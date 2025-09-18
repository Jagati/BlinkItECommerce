package com.lldproject.blinkitecommerce.adapters;

import com.lldproject.blinkitecommerce.models.Location;

import java.util.List;

public interface MapsAdapter {

    public List<Location> buildRoute(List<Location> locations);
}
