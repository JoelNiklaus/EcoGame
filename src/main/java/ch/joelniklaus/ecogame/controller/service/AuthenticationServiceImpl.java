package ch.joelniklaus.ecogame.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidUserException;
import ch.joelniklaus.ecogame.controller.pojos.ForgotPasswordForm;
import ch.joelniklaus.ecogame.controller.pojos.LoginForm;
import ch.joelniklaus.ecogame.controller.pojos.SignupForm;
import ch.joelniklaus.ecogame.model.Address;
import ch.joelniklaus.ecogame.model.Picture;
import ch.joelniklaus.ecogame.model.User;
import ch.joelniklaus.ecogame.model.dao.AddressDao;
import ch.joelniklaus.ecogame.model.dao.PictureDao;
import ch.joelniklaus.ecogame.model.dao.UserDao;

@Service
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	AddressDao addressDao;
	@Autowired
	PictureDao pictureDao;

	private User setVariables(SignupForm signupForm, User user) {
		Picture picture = null;
		try {
			picture = pictureDao.findOne(new Long(signupForm.getImageId()));
		} catch (Exception e) {
			
		}
		user.setProfileImage(picture);

		user.setFirstName(signupForm.getFirstName());
		user.setEmail(signupForm.getEmail());
		user.setLastName(signupForm.getLastName());
		user.setDescription(signupForm.getDescription());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(signupForm.getPassword());
		user.setPassword(hashedPassword);
		
		return user;
	}
	
	private Address setVariables(SignupForm signupForm, Address address) {
		address.setStreet(signupForm.getStreet());
		address.setHouseNr(signupForm.getHouseNr());
		address.setCity(signupForm.getCity());
		address.setZip(signupForm.getZip());
		return address;
	}
	
	@Override
	@Transactional
	public SignupForm createProfile(SignupForm signupForm) throws InvalidUserException {
		User user = setVariables(signupForm, new User());
		Address address = setVariables(signupForm, new Address());
		address = addressDao.save(address);
		user.setAddress(address);
		userDao.save(user);
		signupForm.setId(user.getId());
		return signupForm;
	}
	
	@Override
	@Transactional
	public SignupForm updateProfile(SignupForm profileForm) throws InvalidUserException {
		User user = setVariables(profileForm, userDao.findByEmail(profileForm.getEmail()));
		Address address = setVariables(profileForm, user.getAddress());
		address = addressDao.save(address);
		user.setAddress(address);
		userDao.save(user);
		profileForm.setId(user.getId());
		return profileForm;
	}
	
	@Override
	@Transactional
	public User getUser(ForgotPasswordForm forgotPasswordForm) {
		Iterable<User> users = userDao.findAll();
		User user = null;
		String email = forgotPasswordForm.getEmail();
		
		for (User u : users)
			if (u.getEmail().equals(email))
				user = u;
		
		if (user == null)
			throw new InvalidUserException("No User with this E-Mail exists.");
		
		return user;
	}
	
	@Override
	@Transactional
	public User getUser(LoginForm form) {
		Iterable<User> users = userDao.findAll();
		User user = filterResults(users, form.getEmail(), form.getPassword());
		
		if (user == null)
			throw new InvalidUserException("E-Mail or password incorrect");
		
		return user;
	}
	
	private User filterResults(Iterable<User> users, String email, String password) {
		User user = null;
		
		for (User u : users)
			if (u.getEmail().equals(email) && u.getPassword().equals(password))
				user = u;
		return user;
	}
	
	@Override
	public boolean emailAlreadyExists(String email) {
		boolean exists = false;
		
		Iterable<User> users = userDao.findAll();
		
		SEARCH_MATCH: for (User u : users)
			if (u.getEmail().equals(email)) {
				exists = true;
				break SEARCH_MATCH;
			}
		
		return exists;
	}
	
	@Override
	public User getUser(Long id) {
		User user = userDao.findOne(id);
		if (user == null)
			throw new InvalidUserException("No User with this id existing.");
		return user;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);

		return user;
	}
	
	@Override
	public User getLoggedInUser() {
		return userDao
				.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
