package com.miris.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miris.auth.model.Member;
import com.miris.auth.service.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @FileName : MemberController.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 13

 * @프로그램 설명 : 

 * @변경이력 :
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class MemberController {
	
	@Autowired
    private MemberServiceImpl memberServiceImpl;
	
	@GetMapping
    public List<Member> getAllUsers() {
        return memberServiceImpl.getAllUsers();
    }
	
	@GetMapping("/user")
    public Member getAllUser(String username) {
        return memberServiceImpl.getUser(username);
    }

    @PostMapping
    public Member createUser(@RequestBody Member member) {
        return memberServiceImpl.saveUser(member);
    }
}
