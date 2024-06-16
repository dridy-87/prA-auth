package com.miris.auth.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.auth.model.Member;
import com.miris.auth.repository.MemberRepository;


/**
 * 
 * @FileName : UserDetailsServiceImpl.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 13

 * @프로그램 설명 : JAP UserService

 * @변경이력 :
 */
@Service
public class MemberServiceImpl {
	
	@Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllUsers() {
        return memberRepository.findAll();
    }

    public Member saveUser(Member member) {
        return memberRepository.save(member);
    }
    
    public Member getUser(String username) {
    	return memberRepository.findByName(username);
    }
}
