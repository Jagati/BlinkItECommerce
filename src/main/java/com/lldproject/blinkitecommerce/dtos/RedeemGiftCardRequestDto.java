package com.lldproject.blinkitecommerce.dtos;

import lombok.Data;

@Data
public class RedeemGiftCardRequestDto {
    private double amountToRedeem;
    private int giftCardId;
}
