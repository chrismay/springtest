package uk.chrismay.springtest.config.root;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

public class PersistenceConfigurationTest {

	@Test
	public void canGetEmfBean(){
		DataSource ds= mock(DataSource.class);
		JpaVendorAdapter adapter = mock(JpaVendorAdapter.class);
		PersistenceConfiguration config= new PersistenceConfiguration();
		config.setDatasource(ds);
		config.setVendorAdapter(adapter);
		LocalContainerEntityManagerFactoryBean emf = config.entityManagerFactoryBean();
		assertNotNull(emf);
	}
	
	@Test
	public void canGetTransactionManager(){
		DataSource ds= mock(DataSource.class);
		PersistenceConfiguration config= new PersistenceConfiguration();
		config.setDatasource(ds);
		PlatformTransactionManager tm = config.transactionManager();
		assertNotNull(tm);
	}
	
	@Test
	public void canGetExceptionTranslator(){
		PersistenceConfiguration config= new PersistenceConfiguration();
		PersistenceExceptionTranslationPostProcessor pet = config.exceptionTranslation();
		assertNotNull(pet);
	}
	
}
