package com.lldproject.blinkitecommerce.services;

import com.lldproject.blinkitecommerce.models.PartnerTaskMapping;

import java.util.List;

public interface MatchPartnerTaskService {

    public List<PartnerTaskMapping> matchPartnersAndTasks(List<Long> partnerIds, List<Long> taskIds);
}
