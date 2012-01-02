package uk.chrismay.springtest.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan( basePackages = {"uk.chrismay.springtest.web.controller"} )
@EnableWebMvc
public class WebConfiguration{
	
   @Bean
   public InternalResourceViewResolver viewResolver(){
	   InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	   resolver.setPrefix("/WEB-INF/jsp/");
	   resolver.setSuffix(".jsp");
	   return resolver;
   }
}