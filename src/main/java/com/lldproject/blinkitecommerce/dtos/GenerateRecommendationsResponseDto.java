package com.lldproject.blinkitecommerce.dtos;

import com.lldproject.blinkitecommerce.models.Product;
import lombok.Data;

import java.util.List;

@Data
public class GenerateRecommendationsResponseDto {

    private List<Product> recommendations;
    private ResponseStatus responseStatus;
}
