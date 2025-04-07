package com.sirius1b.auth.repos;

import com.sirius1b.auth.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByValue(String value);
}
