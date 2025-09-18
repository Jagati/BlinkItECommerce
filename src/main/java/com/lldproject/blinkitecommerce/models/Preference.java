package com.lldproject.blinkitecommerce.models;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

//import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.persistence.Entity;

@Data
@Entity
@Getter
//@SessionAttributes
//Preferences of the ads for the user
public class Preference extends BaseModel{
    private String category;
    private String description;
    private Date createdAt;
}
