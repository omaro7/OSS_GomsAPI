package kr.co.goms.web.oss.biz.service.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;

@Component
@Data
public class UserDto {

	public UserDto() {
	}

	private String mb_id;
	private String mb_pwd;
	private String mb_name;
	private String mb_role;
	private String token;
	
	Collection<? extends GrantedAuthority> authorities;
	
}
