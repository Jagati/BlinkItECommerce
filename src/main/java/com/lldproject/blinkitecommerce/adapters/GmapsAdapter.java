package com.lldproject.blinkitecommerce.adapters;

import com.lldproject.blinkitecommerce.libraries.gmaps.GLocation;
import com.lldproject.blinkitecommerce.libraries.gmaps.GMaps;
import com.lldproject.blinkitecommerce.models.Location;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GmapsAdapter implements MapsAdapter{
    @Override
    public List<Location> buildRoute(List<Location> locations) {
        List<GLocation> gLocations = locations.stream().map(location -> {
            GLocation gLocation = new GLocation();
            gLocation.setLat(location.getLatitude());
            gLocation.setLng(location.getLongitude());
            return gLocation;
        }).collect(Collectors.toList());
        GMaps gmaps = new GMaps();
        List<GLocation> optimizedLocations = gmaps.getOptimizedRoute(gLocations);
        return optimizedLocations.stream().map(optimizedLocation -> new Location((int) optimizedLocation.getLat(), (int) optimizedLocation.getLng())).toList();
    }
}
