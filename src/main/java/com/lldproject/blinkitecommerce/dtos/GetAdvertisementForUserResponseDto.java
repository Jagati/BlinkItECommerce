package com.lldproject.blinkitecommerce.dtos;

import com.lldproject.blinkitecommerce.models.Advertisement;
import lombok.Data;

@Data
public class GetAdvertisementForUserResponseDto {
    private Advertisement advertisement;
    private ResponseStatus responseStatus;
}
