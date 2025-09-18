package com.lldproject.blinkitecommerce.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Task extends BaseModel{
    private long customerId;
    @ManyToOne (cascade = CascadeType.ALL)
    private Location pickupLocation;
}
