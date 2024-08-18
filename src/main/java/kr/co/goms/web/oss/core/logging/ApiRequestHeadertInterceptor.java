package kr.co.goms.web.oss.core.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class ApiRequestHeadertInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	String xAuth = request.getHeader("X-Auth") == null ? "" : request.getHeader("X-Auth");
    	String authorization = request.getHeader("Authorization") == null ?"": request.getHeader("Authorization");
    	Cookie[] cookies = request.getCookies() == null ? null : request.getCookies();
    	String cooki = "";
    	if(cookies != null) {
    		cooki = cookies.toString();
    	}
    	
        log.debug(
				"\n**************************** BEGIN[HEAD] **************************\n" + 
				"\t - X-Auth:\t{}\n" + 
				"\t - Authorization:\t{}\n" + 
				"\t - Cookies:\t{}" + 
				"\n**************************** E N D[HEAD] **************************",
				xAuth,
				authorization,
				cooki
			);   
        
		String authCode = "authCode";
		
		/*
		if(xAuth.equals(authCode)) {
			return true;
		}else {
			response.sendError(401, "Not Authorized");
			return false;
		}
		*/
		
		//JHHAN 20240707 X-Auth와 상관없이 무조건 true 처리 > 추후 jwToken Bearer로 처리 예정
		return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	//log.debug("\n********************************* E N D *****************************");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}