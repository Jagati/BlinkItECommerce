package com.lldproject.blinkitecommerce.dtos;

import com.lldproject.blinkitecommerce.models.Order;
import lombok.Data;

@Data
public class PlaceOrderResponseDto {
    private Order order;
    private ResponseStatus status;
}
