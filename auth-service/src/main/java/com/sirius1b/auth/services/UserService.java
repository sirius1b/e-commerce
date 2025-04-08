package com.sirius1b.auth.services;

import com.sirius1b.auth.configs.SecurityJwtConfig;
import com.sirius1b.auth.dtos.UserInfoDto;
import com.sirius1b.auth.exceptions.*;
import com.sirius1b.auth.models.Role;
import com.sirius1b.auth.models.Token;
import com.sirius1b.auth.models.User;
import com.sirius1b.auth.repos.RoleRepository;
import com.sirius1b.auth.repos.UserRepository;
import com.sirius1b.auth.utils.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;
    private JwtService jwtService;
    private RoleRepository roleRepository;
    private TokenCacheService tokenCacheService;
    private SecurityJwtConfig jwtConfig;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder encoder,
                       JwtService jwtService,
                       RoleRepository roleRepository,
                       TokenCacheService redisService,
                       SecurityJwtConfig jwtConfig){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;
        this.tokenCacheService = redisService;
        this.jwtConfig = jwtConfig;
    }

    public User register(String name,
                         String email,
                         String password) throws RoleNotFoundException, UserExistsException {

        if (! userRepository.findByEmail(email).isEmpty()){
            throw new UserExistsException("DUPLICATE EMAIL!");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(encoder.encode(password));

        Role userRole = roleRepository.findByValue(Roles.USER.toString()).orElse(null);

        if (userRole == null){
            throw new RoleNotFoundException("NOT FOUND USER ROLE");
        }

        user.setRoles(Collections.singletonList(userRole));

        return userRepository.save(user);
    }

    public Token login(String email, String password) throws UserNotFoundException, CredentialException {
        User user  = userRepository.findByEmail(email).orElse(null);

        if (user == null){
            throw new UserNotFoundException("User not found by email: " + email);
        }

        if (! encoder.matches(password, user.getHashedPassword())){
            throw new CredentialException("Wrong Password");
        }

        Token token = getUserToken(user);

        tokenCacheService.saveToken(token.getValue(), token.getExpiryAt());
        return token;
    }

    public void logout (String value) throws TokenNotFoundException {
        tokenCacheService.deleteToken(value);
    }

    private Token getUserToken(User user){
        Token token = new Token();
        token.setExpiryAt(System.currentTimeMillis() + jwtConfig.expirationTime());
        token.setUser(user);
        token.setValue(jwtService.generateToken(user));
        return token;
    }

    public List<String> extractRoles(String token)  {
            Collection<GrantedAuthority> grantedAuthorities = jwtService.extractAuthorities(token);
            return grantedAuthorities
                    .stream()
                    .map(e -> e.toString())
                    .collect(Collectors.toList());
    }

    public UserInfoDto getUser(String email) throws UserNotFoundException {
        User user  = userRepository.findByEmail(email).orElse(null);

        if (user == null){
            throw new UserNotFoundException("User not found by email: " + email);
        }

        return UserInfoDto.from(
                user.getName(),
                user.getEmail(),
                user.isEmailVerified(),
                user.getRoles().stream().map(Role::getValue).toList()
        );
    }

    public void updateUser(String email, String name, String newEmail) throws UserNotFoundException {
        User user  = userRepository.findByEmail(email).orElse(null);

        if (user == null){
            throw new UserNotFoundException("User not found by email: " + email);
        }

        user.setEmail(email);
        user.setName(name);
        userRepository.save(user);
    }
}
