package fr.supralog.technicalTest.services.impl;

import org.springframework.stereotype.Service;

import fr.supralog.technicalTest.dto.response.UserResponse;
import fr.supralog.technicalTest.repositories.UserRepository;
import fr.supralog.technicalTest.services.UserSevice;

@Service
public class UserSeviceImpl implements UserSevice {

	public final UserRepository userRepository;
	
	public UserSeviceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void createUser() {
		
	}

	@Override
	public UserResponse getUserById(Long userId) {
		
		return null;
	}

}
