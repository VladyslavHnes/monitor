package com.eco.monitor.service;

import com.eco.monitor.dto.UserDto;
import com.eco.monitor.entity.User;
import com.eco.monitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDto addUser(String email, String password) throws Exception {
        if(repository.findByEmail(email).isPresent()) {
            throw new Exception();
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = repository.save(user);
        return new UserDto(savedUser.getId(), savedUser.getEmail(), savedUser.getPassword());
    }

}
