package com.lldproject.blinkitecommerce.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Partner extends BaseModel{
    private String name;
    @Embedded
    @ManyToOne(cascade = CascadeType.ALL)
    private Location currentLocation;
}
