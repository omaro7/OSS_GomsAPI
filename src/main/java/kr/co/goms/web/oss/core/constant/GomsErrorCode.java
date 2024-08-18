package kr.co.goms.web.oss.core.constant;

import kr.co.goms.web.oss.core.security.exception.BasicExceptionCode;
import lombok.Getter;

@Getter
public enum GomsErrorCode implements BasicExceptionCode {

	ERROR_SYS_99("SYS_99", "시스템 오류입니다[{0}]")
	;
	
	private final String code;
	private final String message;
	
	GomsErrorCode(String code, String message){
		this.code = code;
		this.message = message;
	}

}
