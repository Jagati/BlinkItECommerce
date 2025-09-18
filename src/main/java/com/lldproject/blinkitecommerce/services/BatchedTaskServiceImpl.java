package com.lldproject.blinkitecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lldproject.blinkitecommerce.adapters.MapsAdapter;
import com.lldproject.blinkitecommerce.exceptions.BatchedTaskNotFoundException;
import com.lldproject.blinkitecommerce.models.BatchedTask;
import com.lldproject.blinkitecommerce.models.Location;
import com.lldproject.blinkitecommerce.models.Task;
import com.lldproject.blinkitecommerce.repositories.BatchedTaskRepository;
import com.lldproject.blinkitecommerce.repositories.TaskRepository;

@Service
public class BatchedTaskServiceImpl implements BatchedTaskService {
    private final BatchedTaskRepository batchedTaskRepository;
    private final TaskRepository taskRepository;
    private MapsAdapter mapsAdapter;

    @Autowired
    public BatchedTaskServiceImpl(BatchedTaskRepository batchedTaskRepository, TaskRepository taskRepository, MapsAdapter mapsAdapter){
        this.batchedTaskRepository=batchedTaskRepository;
        this.taskRepository=taskRepository;
        this.mapsAdapter=mapsAdapter;
    }

    @Override
    public List<Location> buildRoute(long batchedTaskId) throws BatchedTaskNotFoundException{
        System.out.println("Entered buildRoute method...");
        Optional<BatchedTask> batchedTaskOp = batchedTaskRepository.findById(batchedTaskId);
        if(batchedTaskOp.isEmpty()){
            throw new BatchedTaskNotFoundException("Batched task not found");
        }
        BatchedTask batchedTask = batchedTaskOp.get();
        System.out.println("Batched Task: "+batchedTask.getId());
        List<Task> tasksInBatch = batchedTask.getTasks();
        System.out.println("Tasks in the batched task are: "+tasksInBatch);
        List<Location> locationList = new ArrayList<>();
        for(Task task: tasksInBatch){
            locationList.add(task.getPickupLocation());
        }
        return mapsAdapter.buildRoute(locationList);
    }
}
