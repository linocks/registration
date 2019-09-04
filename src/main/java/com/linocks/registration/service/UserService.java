package com.linocks.registration.service;

import com.linocks.registration.model.Users;
import com.linocks.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Users saveUser(Users users){

        return userRepository.save(users);
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }
}
