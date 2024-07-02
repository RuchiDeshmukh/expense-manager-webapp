package com.expense.manager.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expense.manager.dto.UserDTO;
import com.expense.manager.entity.User;
import com.expense.manager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	private final ModelMapper modelMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	
	public void save(UserDTO userDTO) {
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = mapToEntity(userDTO);
		user.setUserId(UUID.randomUUID().toString());
		userRepository.save(user);
	}


	private User mapToEntity(UserDTO userDTO) {
		return modelMapper.map(userDTO, User.class);
	}
	
}
