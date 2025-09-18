package com.lldproject.blinkitecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Task extends BaseModel{
    private long customerId;
    @ManyToOne (cascade = CascadeType.ALL)
    private Location pickupLocation;
}
