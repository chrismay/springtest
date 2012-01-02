package uk.chrismay.springtest.config.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ImportResource({ "classpath*:/spring-container-datasource.xml" })
@ComponentScan(basePackages = { "uk.chrismay.springtest.service" })
public class ApplicationConfiguration {

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();

	}
}
