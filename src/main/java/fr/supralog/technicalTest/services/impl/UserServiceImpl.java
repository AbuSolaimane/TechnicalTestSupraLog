package fr.supralog.technicalTest.services.impl;

import org.springframework.stereotype.Service;

import fr.supralog.technicalTest.dto.request.UserRequest;
import fr.supralog.technicalTest.dto.response.UserResponse;
import fr.supralog.technicalTest.repositories.UserRepository;
import fr.supralog.technicalTest.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	public final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void createUser(UserRequest userRequest) {
		
	}

	@Override
	public UserResponse getUserById(Long userId) {
		
		return null;
	}

}
