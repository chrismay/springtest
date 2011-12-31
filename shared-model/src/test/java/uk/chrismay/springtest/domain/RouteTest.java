package uk.chrismay.springtest.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@ContextConfiguration(locations={"/spring-validation.xml"})
public class RouteTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	private Validator validator;
	
	@Test
	public void testCanSetName(){
		Route r = new Route();
		r.setName("blah");
		assertEquals("blah", r.getName());
	}
	@Test
	public void testValidationOfNullName(){
		Route nullName = new Route(null);
		Errors errors = new DirectFieldBindingResult(nullName, "null route");
		validator.validate(nullName,errors);
		assertEquals(1, errors.getErrorCount());
		assertNotNull(errors.getFieldError("name"));
	}
	
	@Test
	public void testValidationEmptyName(){
		Route emptyName = new Route("");
		Errors errors = new DirectFieldBindingResult(emptyName, "null route");
		validator.validate(emptyName,errors);
		assertEquals(1, errors.getErrorCount());
		assertNotNull(errors.getFieldError("name"));		
	}
	@Test
	public void testValidationValidName(){
		Route emptyName = new Route("test");
		Errors errors = new DirectFieldBindingResult(emptyName, "null route");
		validator.validate(emptyName,errors);
		assertEquals(0, errors.getErrorCount());
		
	}
	
	@Test
	public void canGetAndSetId(){
		Route r = new Route("Test");
		r.setId(23);
		assertEquals(23, r.getId());
	}

}
