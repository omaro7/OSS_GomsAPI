package kr.co.goms.web.oss.core.security.exception;

import org.zalando.problem.AbstractThrowableProblem;

import kr.co.goms.web.oss.core.constant.GomsErrorCode;

public class GomsException extends AbstractThrowableProblem{
	
	private static final long serialVersionUID = 1443881556781409821L;
	private final BasicExceptionCode examExceptionCode;
	private final Object[]   examExceptionParam;

	public GomsException(BasicExceptionCode exceptionCode) {
		this.examExceptionCode = exceptionCode;
		this.examExceptionParam = null;
	}
	public GomsException(BasicExceptionCode exceptionCode, Object... params) {
		this.examExceptionCode = exceptionCode;
		this.examExceptionParam = params;
	}
	public GomsException(GomsErrorCode errorSys99, String concat) {
		this.examExceptionCode = null;
		this.examExceptionParam = null;
		// TODO Auto-generated constructor stub
	}
}
