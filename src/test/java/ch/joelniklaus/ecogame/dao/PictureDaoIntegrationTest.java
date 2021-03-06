package ch.joelniklaus.ecogame.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.model.Picture;
import ch.joelniklaus.ecogame.model.dao.PictureDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring*.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PictureDaoIntegrationTest {
	
	@Autowired
	PictureDao pictureDao;

	@Test
	public void testFindByFileName() {
		String PATH =  "/Skeleton/src/main/webapp/img/picture.jpg";
    	
    	Picture picture = new Picture();
    	picture.setFilePath(PATH);
    	
        picture =  pictureDao.save(picture);   
        assertEquals(picture.getFilePath(), PATH);
	}

}
