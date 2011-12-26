package uk.chrismay.springtest.dao.jpa;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import uk.chrismay.springtest.dao.RouteDao;
import uk.chrismay.springtest.domain.Route;

@ContextConfiguration(locations = { "/hsql-tests.xml","/spring-persistence.xml" })
public class RouteDaoJpaTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private RouteDao routeDao;

	@Test
	public void saveAllocatesAnId(){
		Route r = new Route("testy");
		Route saved = routeDao.save(r);
		assertTrue(saved.getId() > 0);

	}
}
