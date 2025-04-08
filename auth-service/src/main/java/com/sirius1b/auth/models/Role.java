package com.sirius1b.auth.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
public class Role extends BaseModel implements GrantedAuthority {
    @Column(nullable = false, unique = true)
    private String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
