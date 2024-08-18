package kr.co.goms.web.oss.core.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.goms.web.oss.core.security.jwt.NoopPasswordEncoder;

@Configuration
public class PasswrodConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
	  Map<String, PasswordEncoder> encoders = new HashMap<>();

	  encoders.put("noop", new NoopPasswordEncoder());
	  encoders.put("bcrypt", new BCryptPasswordEncoder());

	  return new DelegatingPasswordEncoder("bcrypt", encoders);
	}
}