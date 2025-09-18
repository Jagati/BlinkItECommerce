package com.lldproject.blinkitecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lldproject.blinkitecommerce.dtos.MatchPartnerTaskRequestDto;
import com.lldproject.blinkitecommerce.dtos.MatchPartnerTaskResponseDto;
import com.lldproject.blinkitecommerce.dtos.ResponseStatus;
import com.lldproject.blinkitecommerce.models.PartnerTaskMapping;
import com.lldproject.blinkitecommerce.services.MatchPartnerTaskService;
@Controller
public class MatchPartnerTaskController {
    private final MatchPartnerTaskService matchPartnerTaskService;
    @Autowired
    public MatchPartnerTaskController(MatchPartnerTaskService matchPartnerTaskService){
        this.matchPartnerTaskService = matchPartnerTaskService;
    }
    public MatchPartnerTaskResponseDto matchPartnersAndTasks(MatchPartnerTaskRequestDto requestDto){
        MatchPartnerTaskResponseDto responseDto = new MatchPartnerTaskResponseDto();
        try{
            List<PartnerTaskMapping> partnerTaskMapping = matchPartnerTaskService.matchPartnersAndTasks(requestDto.getPartnerIds(), requestDto.getTaskIds());
            responseDto.setPartnerTaskMappings(partnerTaskMapping);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
