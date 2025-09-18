package com.lldproject.blinkitecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lldproject.blinkitecommerce.dtos.BuildBatchedTaskRouteRequestDto;
import com.lldproject.blinkitecommerce.dtos.BuildBatchedTaskRouteResponseDto;
import com.lldproject.blinkitecommerce.dtos.ResponseStatus;
import com.lldproject.blinkitecommerce.models.Location;
import com.lldproject.blinkitecommerce.services.BatchedTaskService;
@Controller
public class BatchedTaskController {
    private final BatchedTaskService batchedTaskService;

    @Autowired
    public BatchedTaskController(BatchedTaskService batchedTaskService){
        this.batchedTaskService = batchedTaskService;
    }

    public BuildBatchedTaskRouteResponseDto buildRoute(BuildBatchedTaskRouteRequestDto requestDto){
        BuildBatchedTaskRouteResponseDto responseDto = new BuildBatchedTaskRouteResponseDto();
        try{
            List<Location> routeToBeTaken = batchedTaskService.buildRoute(requestDto.getBatchedTaskId());
            responseDto.setRouteToBeTaken(routeToBeTaken);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
