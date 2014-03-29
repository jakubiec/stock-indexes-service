package pl.edu.agh.iosr.sis.core.tests;

import java.util.List;




import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.edu.agh.iosr.sis.core.daos.IndexDAO;
import pl.edu.agh.iosr.sis.core.entities.Index;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/testContext.xml" })
public class IndexDAOTest {
	
	private static final String TEST_SYMBOL = "TST1";
	
	@Autowired
	private IndexDAO indexDAO;

	@Test
	public void findAllTest() {
		List<Index> result = indexDAO.findAll();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(result.get(0).getSymbol(), TEST_SYMBOL);
	}
}
