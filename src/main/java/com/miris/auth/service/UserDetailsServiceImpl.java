package com.miris.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserService userService;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user from database or any other source
        // Here, we are using a hardcoded user for demonstration purposes
        log.info("loadUserByUsername: " + username);
        
        com.miris.auth.model.User user = userService.getUser(username);
        
        
        
        if(user == null) {
        	 throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
        		.username(user.getUsername())
        		.password(new BCryptPasswordEncoder().encode(user.getPassword()))
        		.roles(user.getRole())
        		.build();
        		
    }
}