package kr.co.goms.web.oss.core.security.service;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GomsGlobal {

	private String mbId;
	private String mbPwd;
	private String mbRole;
	
	@Builder
	public GomsGlobal(String mbId, String mbPwd, String mbRole) {
		this.mbId = mbId;
		this.mbPwd = mbPwd;
		this.mbRole =mbRole;
	}
	
}
