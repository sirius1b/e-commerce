package com.sirius1b.auth.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Token extends BaseModel{

    @Column(unique = true)
    private String value;
    @ManyToOne
    private User user;
    private long expiryAt;
}
