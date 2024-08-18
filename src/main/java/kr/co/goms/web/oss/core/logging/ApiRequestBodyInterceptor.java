package kr.co.goms.web.oss.core.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.goms.web.oss.core.api.ApiContext;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApiRequestBodyInterceptor implements HandlerInterceptor {

	private final ObjectMapper objectMapper;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	//JHHAN 20270707 ApiContext 생성
    	ApiContext apiContext = new ApiContext(request, response, handler);
    	String guid = apiContext.getGuid();
    	
    	//JHHAN 20270707 Request에 대한 로그 남기기
        String uri = request.getRequestURI();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String params = request.getQueryString();
        String servletPath = request.getServletPath();
        String controllerName = "";
        String className = "";
        
        if(handler != null && handler instanceof org.springframework.web.method.HandlerMethod) {
        	try {
        		HandlerMethod handlerMethod = (HandlerMethod) handler;
        		controllerName = handlerMethod.getBean().getClass().getName();
        		className = handlerMethod.getMethod().getName();
        	}catch(Exception e) {
        		
        	}
        }
        
        log.debug(
				"\n***************************** BEGIN[BODY] *************************\n" + 
				"\t - GUID:\t{}\n" + 
				"\t - URI:\t\t{}\n" + 
				"\t - URL:\t\t{}\n" + 
				"\t - METHOD:\t{}\n" + 
				"\t - PARAMS:\t{}\n" + 
				"\t - ServletPath:\t{}\n" + 
				"\t - Controller:\t{}\n" + 
				"\t - Class:\t{}" + 
				"\n***************************** E N D[BODY] *************************",
				guid,
				uri,
				url,
				method,
				params,
				servletPath,
				controllerName,
				className
			);        
        
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	//log.debug("\n********************************* E N D *****************************");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    
    @Override    
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {  
    	//log.debug("\n********************************* BEGIN[RES] *****************************");
    	//if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) return;     
    	   
    	final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
	 
    	if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {            
    		if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {       
    			JsonNode reponseData = objectMapper.readTree(cachingResponse.getContentAsByteArray());
    			log.debug(
				"\n***************************** BEGIN[RES] *************************\n" + 
				"\t - Response Body : {}" + 
				"\n***************************** E N D[RES] *************************",
				reponseData
    			);   
    		}      
    	}  
	}
}