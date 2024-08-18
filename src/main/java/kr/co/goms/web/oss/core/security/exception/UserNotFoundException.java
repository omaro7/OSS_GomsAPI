package kr.co.goms.web.oss.core.security.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String userId){
		super("userId ==> '" + userId + "' NotFoundException");
	}
}