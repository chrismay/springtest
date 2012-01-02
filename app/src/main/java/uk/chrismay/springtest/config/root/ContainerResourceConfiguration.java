package uk.chrismay.springtest.config.root;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath*:/spring-container-datasource.xml" })
public class ContainerResourceConfiguration {

}
