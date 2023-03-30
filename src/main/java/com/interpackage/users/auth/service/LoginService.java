package com.interpackage.users.auth.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.interpackage.users.auth.model.AuthToken;
import com.interpackage.users.auth.util.JwtTokenUtil;
import com.interpackage.users.model.User;

@Service
public class LoginService {
    
    @Autowired
    JwtTokenUtil utilToken;


	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
    

    public AuthToken doLogin(String user, String password) throws Exception{
        User test = new User();
        test.setName(user);
        test.setPassword(password);

        Set<String> roles = new HashSet<>();
        roles.add("ADMIN");
        roles.add("CLIENT");

        Set<String> permissions = new HashSet<>();
        permissions.add("READ");
        permissions.add("WRITE");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean matches = passwordEncoder.matches(password, test.getPassword());
        if(matches){

		Map<String, Object> claims = new HashMap<>();
        claims.put("permissions", permissions);
        claims.put("roles", roles);
        final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(test.getName());
            return new AuthToken(utilToken.generateToken(userDetails,claims));
        }
        else{
            return null;
        }

    }

}
