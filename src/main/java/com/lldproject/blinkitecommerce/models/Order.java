package com.lldproject.blinkitecommerce.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name="orders")
public class Order extends BaseModel{
    @ManyToOne
    private User user;
    @ManyToOne
    private Address deliveryAddress;
    @OneToMany(mappedBy = "order")
    @ElementCollection
    private List<OrderDetail> orderDetails;
    @Enumerated
    private OrderStatus orderStatus;
}
