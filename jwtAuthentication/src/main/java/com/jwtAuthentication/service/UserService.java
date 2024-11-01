package com.jwtAuthentication.service;

import com.jwtAuthentication.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    public UserService(UserRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }
    public UserDetails loadUserByUsername(String username)
    {
        Optional<User> userDetail = repository.findByName(username);
        // Converting userDetail to UserDetails
        return userDetail.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));

    }
    public String addUser(User user) {
        com.jwtAuthentication.model.User user1 = null;
        user1.setPassword(encoder.encode(user.getPassword()));
        repository.save(user1);
        return "User Added Successfully";
    }

}
