package com.lldproject.blinkitecommerce.models;

import lombok.Data;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

@Data
@Entity
public class LedgerEntry extends BaseModel{
    @Enumerated
    private TransactionType transactionType;
    private double amount;
    private Date createdAt;
}
