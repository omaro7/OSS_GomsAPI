package kr.co.goms.web.oss.core.security.exception;

import lombok.Getter;

@Getter
public enum GomsExceptionCode implements BasicExceptionCode{

	ERROR_EXAM_01("100", "조회 에러입니다."),
	ERROR_EXAM_99("999", "{0}");
	
	private final String code;
	private final String message;
	
	GomsExceptionCode(String code, String message){
		this.code = code;
		this.message = message;
	}

	
}
