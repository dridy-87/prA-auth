package com.miris.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



/**
 * 
 * @FileName : UserDetailsServiceImpl.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 13

 * @프로그램 설명 : Spring Security 사용자 검증

 * @변경이력 :
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {
	
	private final UserServiceImpl userDetailsServiceImpl;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        
        com.miris.auth.model.User user = userDetailsServiceImpl.getUser(username);
        
        
        
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