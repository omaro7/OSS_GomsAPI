package kr.co.goms.web.oss.core.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.PlainJWT;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.JWTProcessor;

import kr.co.goms.web.oss.core.security.jwt.JwtRequestFilter;
import kr.co.goms.web.oss.core.security.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
//@Import (SecurityProblemSupport.class)
public class SecurityConfig {

	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	// JWT 요청 처리 필터
	@Autowired 
	private JwtRequestFilter jwtRequestFilter;
	
	@Value("${jwt.public.key}")
	RSAPublicKey key;

	@Value("${jwt.private.key}")
	RSAPrivateKey priv;	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 사이트 위변조 요청 방지 > 설정하지않으면 POST가 먹지 않습니다. 
        http.csrf(csrf -> csrf.disable());

		http
        .authorizeHttpRequests((authz) -> authz
    		.requestMatchers("/").permitAll() // /경로는 인증 없이 접근 가능
    		.requestMatchers("/member/**").permitAll() // /경로는 인증 없이 접근 가능  
    		.requestMatchers("/member/register", "/member/registerProc").permitAll() // /경로는 인증 없이 접근 가능    		
            .requestMatchers("/api/authenticate").permitAll()
    		.requestMatchers("/swagger-ui/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN") // /admin 경로는 ADMIN 역할만 접근 가능
    		//.requestMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER")
            .requestMatchers("/api/**").permitAll()
            .anyRequest().permitAll()
            //.anyRequest().authenticated()
        )
        .formLogin((form) -> form
                .loginPage("/login") // 커스텀 로그인 페이지 설정
                .defaultSuccessUrl("/home", true) // 로그인 성공 후 이동할 기본 URL
                .failureUrl("/home?error=true") // 로그인 실패 시 이동할 URL
                .usernameParameter("mbid")
                .passwordParameter("mbpwd")
                .permitAll() // 로그인 페이지는 인증 없이 접근 가능
            )
        .logout((logout) -> logout
                .logoutUrl("/logout") // 로그아웃 URL 설정
                .logoutSuccessUrl("/home?logout=true") // 로그아웃 성공 후 이동할 URL
                .invalidateHttpSession(true) // 로그아웃 시 세션 무효화
                .deleteCookies("JSESSIONID") // 로그아웃 시 쿠키 삭제
                .permitAll() // 로그아웃 URL은 인증 없이 접근 가능
            )
        //.userDetailsService(userDetailsService) // CustomUserDetailsService 설정
        //.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .httpBasic(withDefaults())
        //.exceptionHandling().authenticationEntryPoint(securityProblemSupport)
        ;
		

        /*
        // 로그인 설정
        http.formLogin()
                .loginPage("/user2/login")
                .defaultSuccessUrl("/user2/loginSuccess")
                .failureUrl("/user2/login?success=100)")
                .usernameParameter("uid")
                .passwordParameter("pass");

        // 로그아웃 설정
        http.logout()
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/user2/logout"))
                .logoutSuccessUrl("/user2/login?success=200");

        // 사용자 인증 처리 컴포넌트 서비스 등록
        http.userDetailsService(service);
        */
        return http.build();
    }
	
	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(this.key).build();
	}
    
	@Bean
	JwtEncoder jwtEncoder() {
		RSAKey jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	
    /*
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
     // antMatchers 부분도 deprecated 되어 requestMatchers로 대체
        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
    }
    */
}