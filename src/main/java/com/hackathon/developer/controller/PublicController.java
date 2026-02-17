package com.hackathon.developer.controller;

import com.hackathon.developer.model.User;
import com.hackathon.developer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userServices;

    @PostMapping("/create")
    public boolean post(@RequestBody User user){
        userServices.saveNewEntry(user);
        return true;
    }


}

