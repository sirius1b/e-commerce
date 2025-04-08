package com.sirius1b.auth.services;

import com.sirius1b.auth.configs.SecureAdminConfig;
import com.sirius1b.auth.models.Role;
import com.sirius1b.auth.models.User;
import com.sirius1b.auth.repos.RoleRepository;
import com.sirius1b.auth.repos.UserRepository;
import com.sirius1b.auth.utils.Roles;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DataInitService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private SecureAdminConfig adminConfig;

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
        admin.setName(adminConfig.username());
        admin.setRoles(List.of(roleRepository.findByValue(Roles.ADMIN.toString()).get()));
        admin.setEmail(adminConfig.email());
        admin.setHashedPassword(encoder.encode(adminConfig.password()));

        if (userRepository.findByEmail(adminConfig.email()).isEmpty())
            userRepository.save(admin);
    }

}