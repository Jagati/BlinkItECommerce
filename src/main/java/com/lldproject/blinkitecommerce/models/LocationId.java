package com.lldproject.blinkitecommerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Embeddable
@Data
public class LocationId {
    int latitude;
    int longitude; // The composite key field
}
