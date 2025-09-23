package com.lldproject.blinkitecommerce.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity(name="users")
@Getter
@Setter
public class User extends BaseModel{
    private String name;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private List<Preference> preferences;
}
