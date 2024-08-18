package kr.co.goms.web.oss.core.logging;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApiServletWrappingFilter  extends OncePerRequestFilter {     
	
	@Override    
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws ServletException, IOException { 
		log.debug("ApiServletWrappingFilter >>> ApiServletWrapping 처리");
		ContentCachingRequestWrapper wrappingRequest = new ContentCachingRequestWrapper(request);        
		ContentCachingResponseWrapper wrappingResponse = new ContentCachingResponseWrapper(response);        
		filterChain.doFilter(wrappingRequest, wrappingResponse);        
		wrappingResponse.copyBodyToResponse();   
	}
}
