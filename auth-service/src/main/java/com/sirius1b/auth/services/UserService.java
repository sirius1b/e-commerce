package com.sirius1b.auth.services;

import com.sirius1b.auth.configs.SecurityJwtConfig;
import com.sirius1b.auth.exceptions.CredentialException;
import com.sirius1b.auth.exceptions.RoleNotFoundException;
import com.sirius1b.auth.exceptions.TokenNotFoundException;
import com.sirius1b.auth.exceptions.UserNotFoundException;
import com.sirius1b.auth.models.Role;
import com.sirius1b.auth.models.Token;
import com.sirius1b.auth.models.User;
import com.sirius1b.auth.repos.RoleRepository;
import com.sirius1b.auth.repos.TokenRepository;
import com.sirius1b.auth.repos.UserRepository;
import com.sirius1b.auth.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    private BCryptPasswordEncoder encoder;

    private JwtService jwtService;
    private RoleRepository roleRepository;

    @Autowired
    private SecurityJwtConfig jwtConfig;

    public UserService(UserRepository userRepository,
                       TokenRepository tokenRepository,
                       BCryptPasswordEncoder encoder,
                       JwtService jwtService,
                       RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;

    }

    public User register(String name,
                         String email,
                         String password) throws RoleNotFoundException {

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
        // TODO: update this to token save in redis
        User user  = userRepository.findByEmail(email).orElse(null);

        if (user == null){
            throw new UserNotFoundException("User not found by email: " + email);
        }

        if (! encoder.matches(password, user.getHashedPassword())){
            throw new CredentialException("Wrong Password");
        }

        Token token = getUserToken(user);
        tokenRepository.save(token);
        return token;
    }

    public void logout (String value) throws TokenNotFoundException {
        // TODO: update this for redis removal

        Token token = tokenRepository.findByValue(value).orElse(null);

        if (token == null){
            throw new TokenNotFoundException("Token Not Found");
        }

        tokenRepository.delete(token);
    }

    private Token getUserToken(User user){
        Token token = new Token();
        token.setExpiryAt(System.currentTimeMillis() + jwtConfig.expirationTime());
        token.setUser(user);
        token.setValue(jwtService.generateToken(user));
        return token;
    }
}
