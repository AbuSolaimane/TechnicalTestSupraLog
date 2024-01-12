package fr.supralog.technicalTest.services;

import fr.supralog.technicalTest.dto.request.UserRequest;
import fr.supralog.technicalTest.dto.response.UserResponse;

public interface UserService {
	
	public void createUser(UserRequest userRequest);
	public UserResponse getUserById(Long userId);
}
