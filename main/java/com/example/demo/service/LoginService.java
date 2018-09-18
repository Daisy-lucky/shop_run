package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean judge(String phone,String pass){
        User user=userRepository.findByPhoneAndPassword(phone,pass);
        try {
            if (!(user.getName().equals(null))){
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
}
