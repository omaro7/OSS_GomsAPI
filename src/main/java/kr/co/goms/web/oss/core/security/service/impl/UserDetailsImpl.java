package kr.co.goms.web.oss.core.security.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.goms.web.oss.core.security.service.GomsGlobal;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails, Serializable {

	private static final long serialVersionUID = 2781893338598798764L;

    private String mb_id;
    private String mb_pwd;
    private GomsGlobal gomsGlobal;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    // 계정이 갖는 비밀번호
    @Override
    public String getPassword() {
        return mb_pwd;
    }

    // 계정이 갖는 아이디
    @Override
    public String getUsername() {
        return mb_id;
    }

    // 계정 만료 여부
    // true - 만료 안됨, false - 만료(로그인 불가)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부
    // true - 잠김 안됨, false - 잠김(로그인 불가)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 비밀번호 만료 여부
    // true - 만료 안됨, false - 만료(로그인 불가)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부
    // true - 활성화, false - 비활성화(로그인 불가)
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public void setUsername(String username) {
    	this.mb_id = username;
    }
    
    public void setPassword(String password) {
    	this.mb_pwd = password;
    }

	public GomsGlobal getGomsGlobal() {
		// TODO Auto-generated method stub
		return gomsGlobal;
	}
}
