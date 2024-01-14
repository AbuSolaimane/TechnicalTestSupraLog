package fr.supralog.technicalTest.services;

import java.util.List;

import fr.supralog.technicalTest.common.enums.Country;
import fr.supralog.technicalTest.dto.request.UserRequest;
import fr.supralog.technicalTest.dto.response.UserResponse;

public interface UserService {
	
	void createUser(UserRequest userRequest);
	UserResponse getUserById(Long userId);
	List<UserResponse> getUsersByCountry(Country country);
}
