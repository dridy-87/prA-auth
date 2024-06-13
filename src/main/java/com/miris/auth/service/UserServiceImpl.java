package com.miris.auth.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.auth.model.User;
import com.miris.auth.repository.UserRepository;

/**
 * 
 * @FileName : UserDetailsServiceImpl.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 13

 * @프로그램 설명 : JAP UserService

 * @변경이력 :
 */
@Service
public class UserServiceImpl {
	
	@Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public User getUser(String username) {
    	return userRepository.findByName(username);
    }
}
