package kr.co.goms.web.oss.core.security.jwt;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NoopPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        //return charSequence.toString().equals(s);
    	return true;
    }
}