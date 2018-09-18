package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNameService {

    @Autowired
    private UserRepository userRepository;

    public String getName(int id){
        return userRepository.getOne(id).getName();
    }
}
