package com.lldproject.blinkitecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lldproject.blinkitecommerce.dtos.GetAdvertisementForUserRequestDto;
import com.lldproject.blinkitecommerce.dtos.GetAdvertisementForUserResponseDto;
import com.lldproject.blinkitecommerce.dtos.ResponseStatus;
import com.lldproject.blinkitecommerce.exceptions.UserNotFoundException;
import com.lldproject.blinkitecommerce.models.Advertisement;
import com.lldproject.blinkitecommerce.services.AdsService;
@Controller
public class AdsController {
    private AdsService adsService;
    @Autowired
    public AdsController(AdsService adsService){
        this.adsService = adsService;
    }
    public GetAdvertisementForUserResponseDto getAdvertisementForUser(GetAdvertisementForUserRequestDto requestDto){
        GetAdvertisementForUserResponseDto responseDto = new GetAdvertisementForUserResponseDto();
        try{
            Advertisement advertisement = adsService.getAdvertisementForUser(requestDto.getUserId());
            responseDto.setAdvertisement(advertisement);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(UserNotFoundException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
