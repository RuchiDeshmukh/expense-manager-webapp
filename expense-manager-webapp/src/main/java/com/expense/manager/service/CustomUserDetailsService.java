package com.expense.manager.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.expense.manager.entity.User;
import com.expense.manager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user =  userRepository.findByEmail(email).orElseThrow(
										()-> new UsernameNotFoundException("User not found for the email"));
		return new org.springframework.security.core.userdetails.User(
							user.getEmail(),
							user.getPassword(),
							new ArrayList<>());
	}

}
