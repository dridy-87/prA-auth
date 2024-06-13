package com.miris.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miris.auth.model.User;
import com.miris.auth.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @FileName : UserController.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 13

 * @프로그램 설명 : 

 * @변경이력 :
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
    private UserServiceImpl userService;
	
	@GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
	
	@GetMapping("/user")
    public User getAllUser(String username) {
        return userService.getUser(username);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
