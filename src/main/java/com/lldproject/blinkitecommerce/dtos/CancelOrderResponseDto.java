package com.lldproject.blinkitecommerce.dtos;

import com.lldproject.blinkitecommerce.models.Order;
import lombok.Data;

@Data
public class CancelOrderResponseDto {
    private ResponseStatus status;
    private Order order;
}
