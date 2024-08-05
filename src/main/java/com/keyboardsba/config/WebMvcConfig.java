package com.keyboardsba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.keyboardsba.Interceptor.Permissionlnterceptor;
import com.keyboardsba.common.FileManagerService;

@Configuration // 설정을 위한 Spring Bean
public class WebMvcConfig implements WebMvcConfigurer { 

	@Autowired
	private Permissionlnterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(interceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/error", "/css/**", "/img/**", "/user/sign-out"); 
	}
	
	@Override
	public void addResourceHandlers(
			ResourceHandlerRegistry registry) { 

		registry 
		.addResourceHandler("/images/**") 
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);
		
	}
	
}
