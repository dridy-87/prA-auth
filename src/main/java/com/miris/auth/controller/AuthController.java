package com.miris.auth.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @FileName : AuthController.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 13

 * @프로그램 설명 : 

 * @변경이력 :
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
	/**
	 * 
	 * @Method Name : logout
	
	 * @작성자 : yg87.kim
	
	 * @작성일 : 2024-06-13
	
	 * @프로그램 설명 : 
	
	 * @변경이력 :
	 */
	@GetMapping("/logout")
	public ResponseEntity logout(HttpServletRequest request, @RequestBody HashMap<String, Object> param) {
		
	log.info("api logout");
		
	return ResponseEntity.status(HttpStatus.OK).body("로그아웃 성공");
	}
	
}
