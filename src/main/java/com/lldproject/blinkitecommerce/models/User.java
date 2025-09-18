package com.lldproject.blinkitecommerce.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Data
@Entity(name="users")
@Getter
@Setter
public class User extends BaseModel{
    private String name;
    private String email;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private List<Preference> preferences;
}
