package pl.edu.agh.iosr.sis.core.tests;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.agh.iosr.sis.core.daos.IndexValueDAO;
import pl.edu.agh.iosr.sis.core.entities.IndexValue;
import pl.edu.agh.iosr.sis.core.entities.IndexValueId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/testContext.xml" })
public class IndexValueDAOTest {
	
	private static final String TEST_ID = "TEST1";
	private static final String TEST_SYMBOL = "TST1";
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private IndexValueDAO indexValueDAO;
	
	@PersistenceContext
	private EntityManager em;
	private IndexValueId id;
	private IndexValue indexValue;
	private Date date;
	
	@Before
	public void before() throws ParseException{
		date = new Date();
		id = new IndexValueId();
		id.setSymbol(TEST_ID);
		id.setValueDate(date);
		indexValue = new IndexValue();
		indexValue.setSymbol(TEST_ID);
		indexValue.setValue(BigDecimal.ZERO);
		indexValue.setValueDate(date);
		clearDB();
	}
	
	@Transactional
	private void clearDB() {
		IndexValue iv = em.find(IndexValue.class, id);
		if( iv != null)
			em.remove(iv);
	}
	
	
	@Test
	public void saveTest(){
	
		
		Assert.assertNull(em.find(IndexValue.class, id));
		indexValue = indexValueDAO.save(indexValue);
		Assert.assertNotNull(indexValue);
		indexValue = em.find(IndexValue.class, id);
		Assert.assertNotNull(indexValue);
		Assert.assertEquals(TEST_ID, indexValue.getSymbol());
		Assert.assertEquals(date, indexValue.getValueDate());
		
		
	}
	
	@Test
	public void findInPeriodTest() throws ParseException{
		Date start = sdf.parse("2002-01-01 12:12:12");
		Date end = sdf.parse("2016-01-01 12:12:12");
		Assert.assertEquals(2, indexValueDAO.findInPeriod(TEST_SYMBOL, start, end).size());
		
		start = sdf.parse("2006-01-01 12:12:12");
		end = sdf.parse("2009-01-01 12:12:12");
		Assert.assertEquals(1, indexValueDAO.findInPeriod(TEST_SYMBOL, start, end).size());
		
		
		start = sdf.parse("2009-01-01 12:12:12");
		end = sdf.parse("2011-01-01 12:12:12");
		Assert.assertEquals(0, indexValueDAO.findInPeriod(TEST_SYMBOL, start, end).size());
		
	}
}
