package com.lldproject.blinkitecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Inventory extends BaseModel{
    @OneToOne
    private Product product;
    private int quantity;
}
