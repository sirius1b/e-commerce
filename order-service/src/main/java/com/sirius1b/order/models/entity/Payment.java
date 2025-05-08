package com.sirius1b.order.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseModel {

    @ManyToOne
    private PaymentStatus status;

    @OneToMany(mappedBy = "payment")
    private List<Order> orders;

    @Column(nullable = false)
    private String productId;

}
