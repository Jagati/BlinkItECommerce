package com.lldproject.blinkitecommerce.partnerassignstrategy;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lldproject.blinkitecommerce.models.Partner;
import com.lldproject.blinkitecommerce.models.Task;
@Component
public interface PartnerAssignStrategy {
    public Partner assignPartner(List<Partner> partners, Task task, HashSet<Long> allottedPartnerIds);
}
