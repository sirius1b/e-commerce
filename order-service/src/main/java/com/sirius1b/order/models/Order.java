package com.sirius1b.order.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseModel {

    @ManyToOne
    private OrderStatus status;

    @ManyToOne
    private Payment payment;

    @Column(nullable = false)
    private String productId;

    @Column
    private long quantity;

    @Column
    private String userId;
}
