package kr.co.goms.web.oss.core.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private JwtTokenProvider jwtTokenProvider;
	
   public JwtRequestFilter(JwtTokenProvider tokenProvider) {
      this.jwtTokenProvider = tokenProvider;
   }
	
	// 포함하지 않을 url
	private static final List<String> EXCLUDE_URL =
		Collections.unmodifiableList(
			Arrays.asList(
				"/static/**"
			));
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// jwt local storage 사용 시
		// final String token = request.getHeader("Authorization");
		
		// jwt cookie 사용 시
		
		String token = null;
		if(request.getCookies() != null) {
		
			token = Arrays.stream(request.getCookies())
					.filter(c -> c.getName().equals("gomsToken"))
					.findFirst() .map(Cookie::getValue)
					.orElse(null);
		}
		
		String userId = null;
		String jwtToken = null;
		
		// Bearer token인 경우
		if (token != null && token.startsWith("Bearer ")) {
			jwtToken = token.substring(7);
			try {
				userId = jwtTokenProvider.getUsernameFromToken(jwtToken);
			} catch (SignatureException e) {
				log.error("Invalid JWT signature: {}", e.getMessage());
			} catch (MalformedJwtException e) {
				log.error("Invalid JWT token: {}", e.getMessage());
			} catch (ExpiredJwtException e) {
				log.error("JWT token is expired: {}", e.getMessage());
			} catch (UnsupportedJwtException e) {
				log.error("JWT token is unsupported: {}", e.getMessage());
			} catch (IllegalArgumentException e) {
				log.error("JWT claims string is empty: {}", e.getMessage());
			}
		} else {
			//logger.warn("JWT Token does not begin with Bearer String");
		}
		
		// token 검증이 되고 인증 정보가 존재하지 않는 경우 spring security 인증 정보 저장
		if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			/*
			AdminDTO adminDTO = new AdminDTO();
			try {
				adminDTO = adminService.loadAdminByAdminId(adminId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(jwtTokenProvider.validateToken(jwtToken, adminDTO)) {
				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(adminDTO, null ,adminDTO.getAuthorities());
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			*/
		}
		
		// accessToken 인증이 되었다면 refreshToken 재발급이 필요한 경우 재발급
		try {
			if(userId != null) {
				jwtTokenProvider.reGenerateRefreshToken(userId);
			}
		}catch (Exception e) {
			log.error("[JwtRequestFilter] refreshToken 재발급 체크 중 문제 발생 : {}", e.getMessage());
		}
		
		filterChain.doFilter(request,response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
	}
	
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		String jwt = request.getParameter("access_token");
		if(StringUtils.hasText(jwt)) {
			return jwt;
		}
		return null;
	}
}