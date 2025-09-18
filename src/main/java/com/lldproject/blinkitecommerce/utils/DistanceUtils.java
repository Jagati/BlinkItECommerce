package com.lldproject.blinkitecommerce.utils;

import com.lldproject.blinkitecommerce.models.Location;

public class DistanceUtils {

    public static double calculateDistance(Location location1, Location location2){
        System.out.println("DistanceUtils: calculating distance...");
        return Math.sqrt(Math.pow(location1.getLatitude() - location2.getLatitude(), 2) + Math.pow(location1.getLongitude() - location2.getLongitude(), 2));
    }
}
