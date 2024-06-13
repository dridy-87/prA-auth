/**
 * @FileName : AuthErrorControllerAdvice.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 13

 * @프로그램 설명 : 

 * @변경이력 :
 */
package com.miris.auth.exception.advice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * @FileName : AuthErrorControllerAdvice.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 13

 * @프로그램 설명 : Controller Advice 클래

 * @변경이력 :
 */

@Slf4j
@RestControllerAdvice
public class AuthErrorControllerAdvice {
	
	/**
	 * 
	 * @Method Name : handleRuntimeException
	
	 * @작성자 : yg87.kim
	
	 * @작성일 : 2024. 06. 13
	
	 * @프로그램 설명 : RuntimeException handler
	
	 * @변경이력 :
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> handleRuntimeException(HttpServletRequest request, HttpServletResponse response, Exception ex){
		return setError(request, response, ex);
	}
	
	/**
	 * 
	 * @Method Name : handleException
	
	 * @작성자 : yg87.kim
	
	 * @작성일 : 2024. 06. 13
	
	 * @프로그램 설명 : Exception Handler
	
	 * @변경이력 :
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		return setError(request, response, ex);
	}

	/**
	 * @Method Name : setError
	
	 * @작성자 : yg87.kim
	
	 * @작성일 : 2024. 06. 13
	
	 * @프로그램 설명 : 오류 설정 
	
	 * @변경이력 :
	 */
	private ResponseEntity<Map<String, Object>> setError(HttpServletRequest request, HttpServletResponse response,
			Exception ex) {
		// TODO Auto-generated method stub
		log.info("request" + request.toString());
		log.info("response: " + response.toString());
		log.info("EX: " + ex.toString());
		Map<String, Object> model = new HashMap<String, Object>();
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(model);
	}
}
