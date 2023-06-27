package com.sphinx.project.controller;

import com.sphinx.project.controller.base.BaseResource;
import com.sphinx.project.model.JwtRequest;
import com.sphinx.project.model.JwtResponse;
import com.sphinx.project.model.User;
import com.sphinx.project.repo.UserRepo;
import com.sphinx.project.config.security.jwt.JwtTokenUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 create_by : manhnd43
 **/

@RestController
public class JwtAuthenticationController extends BaseResource {
	private static final Logger log = LogManager.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
    private PasswordEncoder encoder;

	@Autowired
    private UserRepo userRepository;

	@Autowired
	MessageSource messageSource;

	@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletRequest httpServletRequest) throws Exception {
		String message = null;
		final User user = authenticate(authenticationRequest, message);
		final String token = JwtTokenUtil.generateToken(user);
		if (token == null) {
			return ResponseEntity.ok(new JwtResponse("","login.fail", 500));
		}
		return ResponseEntity.ok(new JwtResponse(token,"succes", 200));
	}


	private User authenticate(JwtRequest request, String message) throws Exception {
		User user = null;
		User findUser = userRepository.findByUsername(request.getUsername());
		if (findUser != null) {
			String password = null;
			if (!request.getUsername().equals(findUser.getUsername())) {
				message = "Error";
			}
			if (encoder.matches(request.getPassword(), findUser.getPassword())) {
				password = findUser.getPassword();
			} else if (request.getPassword().equals(findUser.getPassword())) {
				password = request.getPassword();
			}

			try {
				Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), password));
				user = (User)auth.getPrincipal();
				user.setFullname(findUser.getFullname());
				user.setId(findUser.getId());
			} catch (DisabledException e) {
				log.error(e.getMessage());
				throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				log.error(e.getMessage());
				throw new Exception("INVALID_CREDENTIALS", e);
			}

		}
		return user;
	}
}
