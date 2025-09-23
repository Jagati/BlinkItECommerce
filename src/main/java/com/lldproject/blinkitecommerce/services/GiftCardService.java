package com.lldproject.blinkitecommerce.services;

import com.lldproject.blinkitecommerce.exceptions.GiftCardDoesntExistException;
import com.lldproject.blinkitecommerce.exceptions.GiftCardExpiredException;
import com.lldproject.blinkitecommerce.models.GiftCard;

public interface GiftCardService {
    public GiftCard createGiftCard(double amount);

    public GiftCard redeemGiftCard(int giftCardId, double amountToRedeem) throws GiftCardDoesntExistException, GiftCardExpiredException;
}
