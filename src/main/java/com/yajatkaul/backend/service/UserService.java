package com.yajatkaul.backend.service;

import com.yajatkaul.backend.model.User;
import com.yajatkaul.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.insert(user);
    }

    public Optional<User> getUser(String name){
        return userRepository.findByName(name);
    }
}
