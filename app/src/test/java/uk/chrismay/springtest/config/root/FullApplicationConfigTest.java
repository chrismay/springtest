package uk.chrismay.springtest.config.root;

import java.util.Vector;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import uk.chrismay.springtest.dao.jpa.HsqlTestConfiguration;

@ContextConfiguration(classes ={HsqlTestConfiguration.class,ApplicationConfiguration.class, PersistenceConfiguration.class})
@Transactional
public class FullApplicationConfigTest extends AbstractJUnit4SpringContextTests{

	@Test
	public void testDoNothing(){
		assertTrue(true);
	}
	
	@Test	
	public void testWebApplicationContext(){
		
		ServletContext servletContext = mock(ServletContext.class);
		when(servletContext.getInitParameterNames()).thenReturn(new Vector<String>().elements());
		when(servletContext.getAttributeNames()).thenReturn(new Vector<String>().elements());

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setConfigLocation("uk.chrismay.springtest.config.web");
		ctx.setParent(applicationContext);
		ctx.setServletContext(servletContext);
		ctx.refresh();
	}

}
