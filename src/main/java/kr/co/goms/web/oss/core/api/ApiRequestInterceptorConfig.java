package kr.co.goms.web.oss.core.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class ApiRequestInterceptorConfig implements WebMvcConfigurer{

	private final ApiRequestInterceptorConfig apiInterceptor;
	
	public ApiRequestInterceptorConfig(ApiRequestInterceptorConfig apiInterceptor) {
		this.apiInterceptor = apiInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor((HandlerInterceptor) apiInterceptor)
		.addPathPatterns("api/**");
	}
}
