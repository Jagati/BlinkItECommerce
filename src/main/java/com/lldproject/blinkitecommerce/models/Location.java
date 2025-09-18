package com.lldproject.blinkitecommerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
public class Location implements Serializable {
    @EmbeddedId
    private LocationId locationId;
    public Location(int latitude, int longitude){
        if(locationId==null){
            locationId=new LocationId();
        }
        locationId.latitude = latitude;
        locationId.longitude = longitude;
    }
    public int getLatitude(){
        return locationId.getLatitude();
    }
    public int getLongitude(){
        return locationId.getLongitude();
    }
    public void setLatitude(int latitude){
        locationId.setLatitude(latitude);
    }
    public void setLongitude(int longitude){
        locationId.setLongitude(longitude);
    }
}
