package com.lldproject.blinkitecommerce.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Data
@Entity
public class GiftCard extends BaseModel{
    private double amount;
    private Date createdAt;
    private Date expiresAt;
    @OneToMany(fetch = FetchType.EAGER)
    @ElementCollection
    private List<LedgerEntry> ledger;
    private String giftCardCode;
}
