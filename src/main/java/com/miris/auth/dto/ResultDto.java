/**
 * @FileName : ResultDto.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 14

 * @프로그램 설명 : 

 * @변경이력 :
 */
package com.miris.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @FileName : ResultDto.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 14

 * @프로그램 설명 : 서버 응답 Dto	

 * @변경이력 :
 */
@Getter
@Setter
@NoArgsConstructor
public class ResultDto<T> {
	
	private Result_Code code;
	private String message;
	private T data;
	
	
	@Builder
	public ResultDto(Result_Code code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public static ResultDto<?> createErrorResult(String message) {
        return ResultDto.builder()
                .code(Result_Code.ERROR)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> ResultDto<?> createSuccessResult(T data) {
        return ResultDto.builder()
                .code(Result_Code.SUCCESS)
                .message("")
                .data(data)
                .build();
    }
	
}
