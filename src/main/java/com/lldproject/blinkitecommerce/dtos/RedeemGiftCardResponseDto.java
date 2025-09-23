package com.lldproject.blinkitecommerce.dtos;

import com.lldproject.blinkitecommerce.models.GiftCard;
import lombok.Data;

@Data
public class RedeemGiftCardResponseDto {
    private GiftCard giftCard;
    private ResponseStatus responseStatus;
}
