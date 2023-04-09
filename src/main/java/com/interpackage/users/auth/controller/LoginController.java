package com.interpackage.users.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody UserCredential authenticationRequest)
			throws Exception {

		AuthToken token = loginService.doLogin(authenticationRequest.getUser(), authenticationRequest.getPassword());
		if (token != null) {
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@RequestMapping(value = "/verify-token", method = RequestMethod.GET)
	public ResponseEntity<?> verifyToken()
			throws Exception {

		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}



}
