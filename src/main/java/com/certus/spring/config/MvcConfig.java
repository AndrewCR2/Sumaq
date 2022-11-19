package com.certus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Value("${folder}")
	private String folder;

	@Value("${pathFolder}")
	private String pathFolder;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		// registry.addResourceHandler("/ProductoImg/**").addResourceLocations("file:/D:/Documentos/Temp/ProductoImg/");
		registry.addResourceHandler("/UploadsImg/**").addResourceLocations("file:/C:/Users/jsusg/OneDrive/Documentos/Temp/UploadsImg/");

	}

	public String pathImage() {
		return pathFolder;
	}

}
