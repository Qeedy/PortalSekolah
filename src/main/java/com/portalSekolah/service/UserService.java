package com.portalSekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portalSekolah.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		return userRepository.findByEmailAddress(emailAddress)
				.orElseThrow(() -> new UsernameNotFoundException(
						String.format(USER_NOT_FOUND_MSG, emailAddress)));
	}

}
