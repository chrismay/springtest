package uk.chrismay.springtest.config.root;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ImportResource( {"classpath*:/spring-persistence.xml" } )
public class PersistenceConfiguration {

	@Autowired
	private DataSource datasource;

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	public void setVendorAdapter(JpaVendorAdapter vendorAdapter) {
		this.vendorAdapter = vendorAdapter;
	}

	@Autowired
	private JpaVendorAdapter vendorAdapter;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(datasource);
		factoryBean.setPackagesToScan(new String[] { "uk.chrismay.springtest.domain" });

		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setJpaProperties(this.jpaProperties());

		return factoryBean;
	}
    public Properties jpaProperties(){
    	Properties props = new Properties();
    	props.setProperty("hibernate.hbm2ddl.auto", "update");
    	return props;
    }
    @Bean
    public PlatformTransactionManager transactionManager(){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory( 
        this.entityManagerFactoryBean().getObject() );
       
       return transactionManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
       return new PersistenceExceptionTranslationPostProcessor();
    }
}
