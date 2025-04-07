package com.sirius1b.auth.services;

import com.sirius1b.auth.models.Role;
import com.sirius1b.auth.models.User;
import com.sirius1b.auth.repos.RoleRepository;
import com.sirius1b.auth.repos.TokenRepository;
import com.sirius1b.auth.repos.UserRepository;
import com.sirius1b.auth.utils.Roles;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DataInitService {

    private static final String ADMIN_USERNAME = "adminuser";
    private static final String ADMIN_PASSWORD = "adminpasswor";
    private static final String ADMIN_EMIAL = "admin@admin.com";

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostConstruct
    @Transactional
    private void init(){
        // ROLES INIT
        for (Roles roleId: Roles.values()){
            Role role = new Role();
            role.setValue(roleId.toString());
            if (roleRepository.findByValue(roleId.toString()).isEmpty())
                roleRepository.save(role);
        }

        User admin = new User();
        admin.setName(ADMIN_USERNAME);
        admin.setRoles(List.of(roleRepository.findByValue(Roles.ADMIN.toString()).get()));
        admin.setEmail(ADMIN_EMIAL);
        admin.setHashedPassword(encoder.encode(ADMIN_PASSWORD));

        if (userRepository.findByEmail(ADMIN_EMIAL).isEmpty())
            userRepository.save(admin);
    }

}