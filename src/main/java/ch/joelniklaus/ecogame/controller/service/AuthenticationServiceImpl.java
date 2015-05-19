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
import ch.joelniklaus.ecogame.model.Picture;
import ch.joelniklaus.ecogame.model.Player;
import ch.joelniklaus.ecogame.model.dao.PictureDao;
import ch.joelniklaus.ecogame.model.dao.PlayerDao;

@Service
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {

	@Autowired
	PlayerDao playerDao;
	@Autowired
	PictureDao pictureDao;
	@Autowired
	PlayerService playerService;
	@Autowired
	GameService gameService;
	
	private Player setVariables(SignupForm signupForm, Player user) {
		Picture picture = null;
		try {
			picture = pictureDao.findOne(new Long(signupForm.getImageId()));
		} catch (Exception e) {

		}
		user.setProfileImage(picture);
		
		user.setUsername(signupForm.getUsername());
		user.setEmail(signupForm.getEmail());
		user.setStatus(signupForm.getStatus());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(signupForm.getPassword());
		user.setPassword(hashedPassword);

		return user;
	}

	@Override
	@Transactional
	public SignupForm createProfile(SignupForm signupForm) throws InvalidUserException {
		Player user = setVariables(signupForm, new Player());
		
		playerDao.save(user);
		
		playerService.initPlayer(user);
		
		signupForm.setId(user.getId());
		return signupForm;
	}

	@Override
	@Transactional
	public SignupForm updateProfile(SignupForm profileForm) throws InvalidUserException {
		Player user = setVariables(profileForm, playerDao.findByEmail(profileForm.getEmail()));
		
		playerDao.save(user);
		profileForm.setId(user.getId());
		return profileForm;
	}

	@Override
	public Player getPlayer(ForgotPasswordForm forgotPasswordForm) {
		Player user = playerDao.findByEmail(forgotPasswordForm.getEmail());

		if (user == null)
			throw new InvalidUserException("No User with this E-Mail exists.");

		return user;
	}

	@Override
	public Player getPlayer(LoginForm form) {
		Player player = playerDao.findByUsername(form.getUsername());
		if (player.getPassword().equals(form.getPassword()))
			return player;
		else
			throw new InvalidUserException("E-Mail or password incorrect");
	}

	@Override
	public boolean emailAlreadyExists(String email) {
		if (playerDao.findByEmail(email) != null)
			return true;
		return false;
	}

	@Override
	public boolean usernameAlreadyExists(String username) {
		if (playerDao.findByUsername(username) != null)
			return true;
		return false;
	}

	@Override
	public Player getPlayer(Long id) {
		Player player = playerDao.findOne(id);
		if (player == null)
			throw new InvalidUserException("No User with this id existing.");
		return player;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return playerDao.findByUsername(username);
	}

	@Override
	public Player getLoggedInPlayer() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		return playerDao.findByUsername(username);
	}

	@Override
	@Transactional
	public void deletePlayer(Long id) {
		// gameService.deleteGame(gameService.getGameOfLoggedInPlayer().getId());
		playerDao.delete(id);
	}
}
