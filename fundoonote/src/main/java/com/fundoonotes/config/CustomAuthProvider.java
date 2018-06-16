package com.fundoonotes.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.fundoonotes.userservice.User;
import com.fundoonotes.userservice.UserDAO;


public class CustomAuthProvider implements AuthenticationProvider {

	@Autowired
	private UserDAO userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		User user = userRepository.findByEmail(email);
        System.out.println("inside user authentication");
		if (user == null) {
			throw new BadCredentialsException(String.format("The username %s doesn't exist", email));
		}

		if (!BCrypt.checkpw(authentication.getCredentials().toString(), user.getPassword())) {
			throw new BadCredentialsException(String.format("Wrong password"));
		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(user.getRole()));

		return new UsernamePasswordAuthenticationToken(email, authentication.getCredentials(), authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}