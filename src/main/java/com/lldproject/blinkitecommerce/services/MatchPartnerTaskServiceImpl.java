package com.lldproject.blinkitecommerce.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lldproject.blinkitecommerce.models.Partner;
import com.lldproject.blinkitecommerce.models.PartnerTaskMapping;
import com.lldproject.blinkitecommerce.models.Task;
import com.lldproject.blinkitecommerce.partnerassignstrategy.PartnerAssignStrategy;
import com.lldproject.blinkitecommerce.repositories.PartnerRepository;
import com.lldproject.blinkitecommerce.repositories.TaskRepository;

@Service
public class MatchPartnerTaskServiceImpl implements MatchPartnerTaskService {
    private final PartnerRepository partnerRepository;
    private final TaskRepository taskRepository;
    private final PartnerAssignStrategy assignStrategy;
    @Autowired
    public MatchPartnerTaskServiceImpl(PartnerRepository partnerRepository, TaskRepository taskRepository, PartnerAssignStrategy assignStrategy){
        this.partnerRepository=partnerRepository;
        this.taskRepository=taskRepository;
        this.assignStrategy=assignStrategy;
    }
    @Override
    public List<PartnerTaskMapping> matchPartnersAndTasks(List<Long> partnerIds, List<Long> taskIds){
        List<Partner> partners = partnerRepository.findByIdIn(partnerIds);
        System.out.println("List of all partners: "+partners);
        List<Task> tasks = taskRepository.findByIdIn(taskIds);
        System.out.println("List of all tasks: "+tasks);
        List<PartnerTaskMapping> allMappings = new ArrayList<>();
        HashSet<Long> allottedPartnerIds = new HashSet<>();
        for(Task task: tasks){
            Partner closestPartner = assignStrategy.assignPartner(partners, task, allottedPartnerIds);
            if(closestPartner!=null){
                PartnerTaskMapping partnerTaskMapping = new PartnerTaskMapping();
                partnerTaskMapping.setPartner(closestPartner);
                partnerTaskMapping.setTask(task);
                allMappings.add(partnerTaskMapping);
                allottedPartnerIds.add(closestPartner.getId());
            }
            else{
                if(allMappings.size()>0){
                    System.out.println("Could not find a partner for task: "+task.getId());
                    return allMappings;
                }
            }
        }
        System.out.println("All mappings: "+allMappings);
        return allMappings;
    }
}
