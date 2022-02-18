package com.microservice.user.userservice.controller;

import com.microservice.user.userservice.entity.User;
import com.microservice.user.userservice.exceptions.NotFoundException;
import com.microservice.user.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id){

        Optional<User> optional = userService.findById(id);


        log.info("Optional=",optional);
        if(!optional.isPresent())
            throw new NotFoundException("user not found for id="+id);

        return optional;

        //return userService.findById(id).orElseThrow(() -> new NotFoundException("user not found for id="+id));
    }

}
