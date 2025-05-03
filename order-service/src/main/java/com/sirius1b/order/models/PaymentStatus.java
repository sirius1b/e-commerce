package com.sirius1b.order.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PaymentStatus extends BaseModel {

    @Column(nullable = false, unique = true)
    private String value;
}
