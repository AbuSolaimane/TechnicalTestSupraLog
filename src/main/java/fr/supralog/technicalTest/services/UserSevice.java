package fr.supralog.technicalTest.services;

import fr.supralog.technicalTest.dto.response.UserResponse;

public interface UserSevice {
	
	public void createUser();
	public UserResponse getUserById(Long userId);
}
