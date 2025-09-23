package com.lldproject.blinkitecommerce.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lldproject.blinkitecommerce.exceptions.GiftCardDoesntExistException;
import com.lldproject.blinkitecommerce.exceptions.GiftCardExpiredException;
import com.lldproject.blinkitecommerce.models.GiftCard;
import com.lldproject.blinkitecommerce.models.LedgerEntry;
import com.lldproject.blinkitecommerce.models.TransactionType;
import com.lldproject.blinkitecommerce.repositories.GiftCardRepository;
import com.lldproject.blinkitecommerce.repositories.LedgerEntryRepository;
import com.lldproject.blinkitecommerce.utils.GiftCardUtils;

@Service
public class GiftCardServiceImpl implements GiftCardService {
    private final GiftCardRepository giftCardRepository;
    private final LedgerEntryRepository ledgerEntryRepository;
    @Autowired
    public GiftCardServiceImpl(GiftCardRepository giftCardRepository, LedgerEntryRepository ledgerEntryRepository){
        this.giftCardRepository = giftCardRepository;
        this.ledgerEntryRepository = ledgerEntryRepository;
    }

    @Override
    public GiftCard createGiftCard(double amount){
        LedgerEntry ledgerEntry = new LedgerEntry();
        ledgerEntry.setAmount(amount);
        ledgerEntry.setTransactionType(TransactionType.CREDIT);
        Date createdAtDate = new Date();
        ledgerEntry.setCreatedAt(createdAtDate);
        ledgerEntryRepository.save(ledgerEntry);
        GiftCard giftCard = new GiftCard();
        giftCard.setAmount(amount);
        giftCard.setCreatedAt(createdAtDate);
        giftCard.setGiftCardCode(GiftCardUtils.generateGiftCardCode());
        giftCard.setExpiresAt(GiftCardUtils.getExpirationDate(createdAtDate));
        List<LedgerEntry> ledgerEntries = new ArrayList<>();
        ledgerEntries.add(ledgerEntry);
        giftCard.setLedger(ledgerEntries);
        return giftCardRepository.save(giftCard);
    }
    @Override
    public GiftCard redeemGiftCard(int giftCardId, double amountToRedeem) throws GiftCardDoesntExistException, GiftCardExpiredException{
        Optional<GiftCard> giftCardOp = giftCardRepository.findById(giftCardId);
        if(giftCardOp.isEmpty()){
            throw new GiftCardDoesntExistException("Gift card doesn't exist");
        }
        GiftCard giftCard = giftCardOp.get();
        Date expiryDate = giftCard.getExpiresAt();
        if(expiryDate.before(new Date())){
            throw new GiftCardExpiredException("Gift card expired");
        }
        LedgerEntry ledgerEntry = new LedgerEntry();
        ledgerEntry.setCreatedAt(new Date());
        ledgerEntry.setTransactionType(TransactionType.DEBIT);
        //If the giftcard balance is less than the amount to redeem, then use all the giftcard amount
        if(giftCard.getAmount()<amountToRedeem){
            ledgerEntry.setAmount(giftCard.getAmount());
            // The Remaining amount will be paid by user by some other means
            Double balanceToBePaid = amountToRedeem-giftCard.getAmount();
            ledgerEntryRepository.save(ledgerEntry);
            giftCard.setAmount(0);
        }
        else{
            ledgerEntry.setAmount(amountToRedeem);
            ledgerEntryRepository.save(ledgerEntry);
            giftCard.setAmount(giftCard.getAmount()-amountToRedeem);
        }
        List<LedgerEntry> ledgerEntries = giftCard.getLedger();
        ledgerEntries.add(ledgerEntry);
        giftCard.setLedger(ledgerEntries);
        return giftCardRepository.save(giftCard);
    }

}
