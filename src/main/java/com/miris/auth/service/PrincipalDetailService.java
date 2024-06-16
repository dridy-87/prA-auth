package com.miris.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.miris.auth.model.Member;

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
	
	private final MemberServiceImpl userDetailsServiceImpl;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        
        Member member = userDetailsServiceImpl.getUser(username);
        
        
        
        if(member == null) {
        	 throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
        		.username(member.getUsername())
        		.password(new BCryptPasswordEncoder().encode(member.getPassword()))
        		.roles(member.getRole())
        		.build();
        		
    }
}