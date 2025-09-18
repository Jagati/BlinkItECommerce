package com.lldproject.blinkitecommerce.partnerassignstrategy;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lldproject.blinkitecommerce.models.Partner;
import com.lldproject.blinkitecommerce.models.Task;
import com.lldproject.blinkitecommerce.utils.DistanceUtils;

@Component
public class HaversineAssignStrategy implements PartnerAssignStrategy {


    @Override
    public Partner assignPartner(List<Partner> partners, Task task, HashSet<Long> allottedPartnerIds){
        System.out.println("Now we are calculating the closest partner for task: "+task.getId()+ " given partners list: "+partners);
        if(allottedPartnerIds.isEmpty()){ System.out.println("No partner allotted yet.");}
        else{ System.out.println("Allotted partners: "+allottedPartnerIds);}
        Partner closestPartner=null;
        double min_dist = Double.MAX_VALUE;
        for(Partner partner: partners){
            if(allottedPartnerIds.contains(partner.getId())) continue;
            System.out.println("Now calculating distance based on Haversine distance formula.");
            double distance = DistanceUtils.calculateDistance(partner.getCurrentLocation(), task.getPickupLocation());
            System.out.println("Successfully calculated distance as: "+distance);
            if(distance<min_dist){
                min_dist=distance;
                closestPartner=partner;
            }
        }
        if(closestPartner==null){System.out.println("HAS: No partner found!");}
        else{System.out.println("Closest partner: "+closestPartner.getId());}
        return closestPartner;
    }

}
