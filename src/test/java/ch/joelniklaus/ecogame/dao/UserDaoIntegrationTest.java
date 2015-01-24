package ch.joelniklaus.ecogame.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.model.Address;
import ch.joelniklaus.ecogame.model.User;
import ch.joelniklaus.ecogame.model.dao.UserDao;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring*.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserDaoIntegrationTest {

	@Autowired	
    UserDao userDao;

    
    @Test
    public void testAddressReference() {
    	String STREETNAME = "testStreet"; 
    	
    	Address address = new Address();
    	address.setStreet(STREETNAME);	
    	User user = new User();       
        user.setAddress(address);
        user =  userDao.save(user);
        
        assertEquals(user.getAddress().getStreet(), STREETNAME);
    }
    
    @Test
    public void testFindByEmail(){
    	String EMAIL =  "bla@bla.bla";
    	
    	User user = new User();
    	user.setEmail(EMAIL);
    	
        user =  userDao.save(user);        
        User findUser = userDao.findByEmail(EMAIL);
        assertEquals(findUser.getEmail(), EMAIL);
    }

}
