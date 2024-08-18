package kr.co.goms.web.oss.core.exception;

import org.zalando.problem.AbstractThrowableProblem;

import kr.co.goms.web.oss.core.constant.GomsErrorCode;
import lombok.Getter;

@Getter
public class GomsException extends AbstractThrowableProblem{

	private static final long serialVersionUID = 1820840601080649847L;

	private final GomsExceptionCode exceptionCode;
	private final Object[] exceptionParams;

	public GomsException(GomsExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
		this.exceptionParams = null;
	}
	public GomsException(GomsExceptionCode exceptionCode, Object...objects) {
		this.exceptionCode = exceptionCode;
		this.exceptionParams = objects;
	}
	public GomsException(GomsErrorCode errorSys99, String string) {
		this.exceptionCode = null;
		this.exceptionParams = null;
		// TODO Auto-generated constructor stub
	}
}
