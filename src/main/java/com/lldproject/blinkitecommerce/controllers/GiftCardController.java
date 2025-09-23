package com.lldproject.blinkitecommerce.controllers;

import com.lldproject.blinkitecommerce.dtos.CreateGiftCardRequestDto;
import com.lldproject.blinkitecommerce.dtos.CreateGiftCardResponseDto;
import com.lldproject.blinkitecommerce.dtos.RedeemGiftCardRequestDto;
import com.lldproject.blinkitecommerce.dtos.RedeemGiftCardResponseDto;
import com.lldproject.blinkitecommerce.dtos.ResponseStatus;
import com.lldproject.blinkitecommerce.models.GiftCard;
import com.lldproject.blinkitecommerce.services.GiftCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GiftCardController {
    private final GiftCardService giftCardService;
    @Autowired
    public GiftCardController(GiftCardService giftCardService){
        this.giftCardService=giftCardService;
    }


    public CreateGiftCardResponseDto createGiftCard(CreateGiftCardRequestDto requestDto){
        CreateGiftCardResponseDto responseDto = new CreateGiftCardResponseDto();
        try{
            GiftCard giftcard = giftCardService.createGiftCard(requestDto.getAmount());
            responseDto.setGiftCard(giftcard);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public RedeemGiftCardResponseDto redeemGiftCard(RedeemGiftCardRequestDto requestDto){
        RedeemGiftCardResponseDto responseDto = new RedeemGiftCardResponseDto();
        try{
            GiftCard giftcard = giftCardService.redeemGiftCard(requestDto.getGiftCardId(), requestDto.getAmountToRedeem());
            responseDto.setGiftCard(giftcard);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
