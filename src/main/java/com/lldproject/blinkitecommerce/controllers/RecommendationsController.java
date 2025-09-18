package com.lldproject.blinkitecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;

import com.lldproject.blinkitecommerce.dtos.GenerateRecommendationsRequestDto;
import com.lldproject.blinkitecommerce.dtos.GenerateRecommendationsResponseDto;
import com.lldproject.blinkitecommerce.dtos.ResponseStatus;
import com.lldproject.blinkitecommerce.models.Product;
import com.lldproject.blinkitecommerce.services.RecommendationsService;
@Controller
public class RecommendationsController {
    private final RecommendationsService recommendationsService;
    @Autowired
    public RecommendationsController(RecommendationsService recommendationsService){
        this.recommendationsService=recommendationsService;
    }

    public GenerateRecommendationsResponseDto generateRecommendations(GenerateRecommendationsRequestDto requestDto) {
        GenerateRecommendationsResponseDto responseDto = new GenerateRecommendationsResponseDto();
        try{
            List<Product> products = recommendationsService.getRecommendations(requestDto.getProductId());
            responseDto.setRecommendations(products);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
