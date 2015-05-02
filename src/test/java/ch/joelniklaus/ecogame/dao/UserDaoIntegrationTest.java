package ch.joelniklaus.ecogame.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.model.dao.system.UserDao;
import ch.joelniklaus.ecogame.model.system.Address;
import ch.joelniklaus.ecogame.model.system.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/spring*.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserDaoIntegrationTest {

	@Autowired
	UserDao userDao;

	@Test
	public void testAddressReference() {
		String street = "testStreet";
		
		Address address = new Address();
		address.setStreet(street);
		User user = new User();
		user.setAddress(address);
		user = userDao.save(user);

		assertEquals(user.getAddress().getStreet(), street);
	}

	@Test
	public void testFindByEmail() {
		String email = "bla@bla.bla";

		User user = new User();
		user.setEmail(email);

		user = userDao.save(user);
		User findUser = userDao.findByEmail(email);
		assertEquals(findUser.getEmail(), email);
	}

}
