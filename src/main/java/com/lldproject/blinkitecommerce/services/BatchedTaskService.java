package com.lldproject.blinkitecommerce.services;

import com.lldproject.blinkitecommerce.exceptions.BatchedTaskNotFoundException;
import com.lldproject.blinkitecommerce.models.Location;

import java.util.List;

public interface BatchedTaskService {

    public List<Location> buildRoute(long batchedTaskId) throws BatchedTaskNotFoundException;
}
