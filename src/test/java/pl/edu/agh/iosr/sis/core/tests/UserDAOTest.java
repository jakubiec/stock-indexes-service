package pl.edu.agh.iosr.sis.core.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.agh.iosr.sis.core.daos.IndexDAO;
import pl.edu.agh.iosr.sis.core.daos.UserDAO;
import pl.edu.agh.iosr.sis.core.entities.Index;
import pl.edu.agh.iosr.sis.core.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/testContext.xml" })
public class UserDAOTest {

	private static final String TST_LOGIN = "tst";
	private static final String NOT_UNIQUE_LOGIN = "test1";
	private User testUser;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private IndexDAO indexDAO;

	@Before
	@Transactional
	public void before() {
		testUser = new User();
		testUser.setIsAdmin(false);
		testUser.setLogin(TST_LOGIN);
		testUser.setPassword(TST_LOGIN);
	}

	@After
	@Transactional
	public void clearDB() {
		if (testUser.getId() != null) {
			User user = em.find(User.class, testUser.getId());
			if (user != null)
				em.remove(user);
		}
	}

	@Test
	public void findAllTest() {
		List<User> users = userDAO.findAll();
		Assert.assertEquals(2, users.size());
		Assert.assertEquals(1, users.get(0).getAvailableIndexes().size());
		Assert.assertEquals(0, users.get(1).getAvailableIndexes().size());
	}
	
	@Test
	public void findByLoginTest(){
		Assert.assertNull(userDAO.findByLogin(TST_LOGIN));
		User u = userDAO.findByLogin(NOT_UNIQUE_LOGIN);
		Assert.assertNotNull(u);
		Assert.assertEquals(NOT_UNIQUE_LOGIN, u.getLogin());
		
	}
	
	@Test
	@Transactional
	public void saveTest(){
		Assert.assertNull(userDAO.findByLogin(TST_LOGIN));
		testUser = userDAO.save(testUser);
		em.flush();
		User u = userDAO.findByLogin(TST_LOGIN);
		Assert.assertNotNull(u);
		Assert.assertEquals(TST_LOGIN, u.getLogin());
		Assert.assertFalse(u.getIsAdmin());
		Assert.assertEquals(0, u.getAvailableIndexes().size());
		Index index = indexDAO.findAll().get(0);
		u.addAvailableIndex(index);
		u = userDAO.save(u);
		Assert.assertEquals(1, u.getAvailableIndexes().size());
		Assert.assertTrue(u.getAvailableIndexes().contains(index));
		u.removeAvailableIndex(index);
		u = userDAO.save(u);
		Assert.assertEquals(0, u.getAvailableIndexes().size());
		
	}

}
