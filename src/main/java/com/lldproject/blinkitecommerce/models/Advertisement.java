package com.lldproject.blinkitecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Advertisement extends BaseModel{
    private String name;
    private String description;
    @ManyToOne
    private Preference preference;
}
