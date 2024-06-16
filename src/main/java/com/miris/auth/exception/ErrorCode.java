/**
 * @FileName : ErroCode.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 14

 * @프로그램 설명 : 

 * @변경이력 :
 */
package com.miris.auth.exception;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @FileName : ErroCode.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 14

 * @프로그램 설명 : 에러코드 정의

 * @변경이력 :
 */


@AllArgsConstructor
@Getter
public enum ErrorCode {
		
	ACCOUNT_NOT_FOUND(HttpStatus.BAD_REQUEST, "계정 정보를 찾을 수 없습니다.");
	

	private HttpStatus status;
	private final String message;
	
}