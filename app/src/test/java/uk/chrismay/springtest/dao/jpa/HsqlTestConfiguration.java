package uk.chrismay.springtest.dao.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath*:/spring-hsql-datasource.xml" })
public class HsqlTestConfiguration {

}
