package kr.co.goms.web.oss.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.goms.web.oss.core.logging.ApiRequestBodyInterceptor;
import kr.co.goms.web.oss.core.logging.ApiRequestHeadertInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	ApiRequestHeadertInterceptor apiRequestHeaderInterceptor;
	
	@Autowired
	ApiRequestBodyInterceptor apiRequestBodyInterceptor;
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/", "classpath:/public/", "classpath:/",
			"classpath:/resources/", "classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/" , "classpath:/favicon.ico" };
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// /에 해당하는 url mapping을 /index로 forward
		registry.addViewController("/").setViewName("forward:/index");
		// 우선순위를 가장 높게 설정
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	/**
	 * API Request interceptor하기 위해서 addInterceptor 처리
	 */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		
		//HEADER
		registry.addInterceptor(apiRequestHeaderInterceptor).addPathPatterns("/api/**");
		//BODY
        registry.addInterceptor(apiRequestBodyInterceptor).addPathPatterns("/api/**").excludePathPatterns("/css/**", "/images/**", "/js/**");
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
		return (factory) -> factory
			.addContextCustomizers((context) -> context.setCookieProcessor(new LegacyCookieProcessor()));
	}
	*/
}