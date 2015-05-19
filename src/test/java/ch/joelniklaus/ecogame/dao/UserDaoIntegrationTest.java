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

import ch.joelniklaus.ecogame.model.Player;
import ch.joelniklaus.ecogame.model.dao.PlayerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/spring*.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserDaoIntegrationTest {
	
	@Autowired
	PlayerDao userDao;
	
	@Test
	public void testFindByEmail() {
		String email = "bla@bla.bla";
		
		Player user = new Player();
		user.setEmail(email);
		
		user = userDao.save(user);
		Player findUser = userDao.findByEmail(email);
		assertEquals(findUser.getEmail(), email);
	}
	
}
