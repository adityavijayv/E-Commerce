package com.jwtAuthentication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeControllers {

    @GetMapping("/user")
    public String getUser(){
        System.out.println("getting users");
        return "users";
    }
}
