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

	@Override
	@Transactional
	public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException {

		Picture picture = null;

		try {
			picture = pictureDao.findOne(new Long(signupForm.getImageId()));
		} catch (Exception e) {

		}
		
		User user = new User();
		user.setFirstName(signupForm.getFirstName());
		user.setEmail(signupForm.getEmail());
		user.setLastName(signupForm.getLastName());
		user.setDescription(signupForm.getDescription());
		user.setProfileImage(picture);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(signupForm.getPassword());
		user.setPassword(hashedPassword);

		Address address = new Address();
		address.setStreet(signupForm.getStreet());
		address.setHouseNr(signupForm.getHouseNr());
		address.setCity(signupForm.getCity());
		address.setZip(signupForm.getZip());
		address = addressDao.save(address);

		user.setAddress(address);
		user = userDao.save(user); // save object to DB

		signupForm.setId(user.getId());

		return signupForm;
	}

	@Override
	@Transactional
	public SignupForm updateProfile(SignupForm profileForm) throws InvalidUserException {
		
		Picture picture = null;

		try {
			picture = pictureDao.findOne(new Long(profileForm.getImageId()));
		} catch (Exception e) {

		}

		User user = userDao.findByEmail(profileForm.getEmail());
		user.setFirstName(profileForm.getFirstName());
		user.setEmail(profileForm.getEmail());
		user.setLastName(profileForm.getLastName());
		user.setDescription(profileForm.getDescription());
		user.setProfileImage(picture);
		System.err.println(user.getEmail() + "  " + user.getId());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(profileForm.getPassword());
		user.setPassword(hashedPassword);
		user = userDao.save(user); // save object to DB

		Address address = user.getAddress();
		address.setStreet(profileForm.getStreet());
		address.setHouseNr(profileForm.getHouseNr());
		address.setCity(profileForm.getCity());
		address.setZip(profileForm.getZip());
		address = addressDao.save(address);

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
