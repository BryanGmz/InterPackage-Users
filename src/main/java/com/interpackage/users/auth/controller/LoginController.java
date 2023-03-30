package com.interpackage.users.auth.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.interpackage.users.auth.model.AuthToken;
import com.interpackage.users.auth.model.UserCredential;
import com.interpackage.users.auth.service.LoginService;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	private AuthenticationManager authenticationManager;


	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody UserCredential authenticationRequest)
			throws Exception {
		
		authenticate(authenticationRequest.getUser(), authenticationRequest.getPassword());

		AuthToken token = loginService.doLogin(authenticationRequest.getUser(), authenticationRequest.getPassword());
		System.out.println("finishinglogin");
		if (token != null) {
			System.out.println("yes");
			return ResponseEntity.ok(token);
		}
		System.out.println("nop");

		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
