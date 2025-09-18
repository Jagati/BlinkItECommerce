package com.lldproject.blinkitecommerce.controllers;

import com.lldproject.blinkitecommerce.dtos.BuildBatchedTaskRouteRequestDto;
import com.lldproject.blinkitecommerce.dtos.BuildBatchedTaskRouteResponseDto;
import com.lldproject.blinkitecommerce.dtos.ResponseStatus;
import com.lldproject.blinkitecommerce.models.BatchedTask;
import com.lldproject.blinkitecommerce.models.Location;
import com.lldproject.blinkitecommerce.models.Task;
import com.lldproject.blinkitecommerce.repositories.BatchedTaskRepository;
import com.lldproject.blinkitecommerce.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestBatchedTaskController {

    @Autowired
    private BatchedTaskController batchedTaskController;

    @Autowired
    private BatchedTaskRepository batchedTaskRepository;

    @Autowired
    private TaskRepository taskRepository;

    private BatchedTask batchedTask;

    @BeforeEach
    public void insertDummyData(){

        Task task1 = new Task();
        task1.setCustomerId(1L);
        task1.setPickupLocation(new Location(10, 10));

        Task task2 = new Task();
        task2.setCustomerId(2L);
        task2.setPickupLocation(new Location(20, 20));

        Task task3 = new Task();
        task3.setCustomerId(3L);
        task3.setPickupLocation(new Location(30, 30));

        task1 = taskRepository.save(task1);
        task2 = taskRepository.save(task2);
        task3 = taskRepository.save(task3);

        batchedTask = new BatchedTask();
        batchedTask.setTasks(Arrays.asList(task1, task2, task3));
        batchedTask.setBatchedAt(new Date());
        batchedTask = batchedTaskRepository.save(batchedTask);

    }

    @Test
    public void buildRoute_BatchedTaskDoesntExist(){
        BuildBatchedTaskRouteRequestDto requestDto = new BuildBatchedTaskRouteRequestDto();
        requestDto.setBatchedTaskId(100L);
        BuildBatchedTaskRouteResponseDto responseDto = batchedTaskController.buildRoute(requestDto);
        assertEquals(ResponseStatus.FAILURE, responseDto.getStatus());
    }

    @Test
    public void buildRoute_Success(){
        BuildBatchedTaskRouteRequestDto requestDto = new BuildBatchedTaskRouteRequestDto();
        requestDto.setBatchedTaskId(batchedTask.getId());
        BuildBatchedTaskRouteResponseDto responseDto = batchedTaskController.buildRoute(requestDto);
        assertEquals(ResponseStatus.SUCCESS, responseDto.getStatus());
        assertEquals(3, responseDto.getRouteToBeTaken().size());
    }

}
