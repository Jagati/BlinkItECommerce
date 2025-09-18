package com.lldproject.blinkitecommerce.models;

import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Data
@Entity
public class BatchedTask extends BaseModel {
    @OneToMany(fetch = FetchType.EAGER)
    private List<Task> tasks;
    private Date batchedAt;
}
