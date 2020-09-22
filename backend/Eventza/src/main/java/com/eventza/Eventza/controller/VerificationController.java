package com.eventza.Eventza.controller;

import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {

    @Autowired
    UserService service;

    @Autowired
    UserRepository repo;

    @GetMapping("/verify/{code}")
    public boolean verify(@PathVariable String code){
        System.out.println("Inside verify");
        User user = service.getUserByVerificationToken(code);
        if(user!=null){
            user.setVerified(true);
            repo.save(user);
            return true;
        }
        else{
            return false;
        }
    }
}
