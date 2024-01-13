package fr.supralog.technicalTest.services.impl;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.supralog.technicalTest.common.enums.Country;
import fr.supralog.technicalTest.common.exceptions.BusinessRuleException;
import fr.supralog.technicalTest.common.exceptions.UserNotFoundException;
import fr.supralog.technicalTest.common.mappers.UserMapper;
import fr.supralog.technicalTest.domain.UserEntity;
import fr.supralog.technicalTest.dto.request.UserRequest;
import fr.supralog.technicalTest.dto.response.UserResponse;
import fr.supralog.technicalTest.repositories.UserRepository;
import fr.supralog.technicalTest.services.UserService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	public final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void createUser(UserRequest userRequest) {
		
		validate(userRequest);
		
		userRepository.save(UserMapper.mapToEntity(userRequest));
	}

	private void validate(UserRequest userRequest) {
		
		if (!userRequest.password().equals(userRequest.confirmedPassword()))
			throw new BusinessRuleException("errors.confirmed.password.match", HttpStatus.BAD_REQUEST);
		
		if(!userRequest.country().equals(Country.FRANCE))
			throw new BusinessRuleException("errors.confirmed.password.match", HttpStatus.BAD_REQUEST);
		
		if(userRequest.birthday().plusYears(18).isAfter(LocalDate.now()))
			throw new BusinessRuleException("errors.confirmed.password.match", HttpStatus.BAD_REQUEST);
	}

	@Override
	public UserResponse getUserById(Long userId) {
		
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("errors.user.not.found", new String[] { userId.toString() }));
		
		return UserMapper.mapToDto(userEntity);
	}

}
