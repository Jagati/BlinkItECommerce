package com.lldproject.blinkitecommerce.dtos;

import com.lldproject.blinkitecommerce.models.GiftCard;
import lombok.Data;

@Data
public class CreateGiftCardResponseDto {
    private GiftCard giftCard;
    private ResponseStatus responseStatus;
}
