 package ch.joelniklaus.ecogame.service;


import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidUserException;
import ch.joelniklaus.ecogame.controller.pojos.SignupForm;
import ch.joelniklaus.ecogame.controller.service.LoginService;
import ch.joelniklaus.ecogame.controller.service.LoginServiceImpl;
import ch.joelniklaus.ecogame.model.User;
import ch.joelniklaus.ecogame.model.dao.UserDao;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    private UserDao userDao;
    private LoginService loginServiceImpl;

    @Before
    public void doSetup() {
        userDao = mock(UserDao.class);
        loginServiceImpl = new LoginServiceImpl();
    }


    @Test(expected = InvalidUserException.class)
    public void testInvalidUserException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setLastName("formLast");
        signupForm.setFirstName("ESE");
        signupForm.setEmail("form@test.com");

        loginServiceImpl.saveFrom(signupForm);
    }


}