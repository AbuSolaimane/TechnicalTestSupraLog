package fr.supralog.technicalTest.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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

/**
 * Service implementation for managing user-related operations.
 * Provides methods for creating and retrieving user information.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	/**
     * Constructs a new UserServiceImpl with the specified UserRepository and PasswordEncoder.
     *
     * @param userRepository The repository for accessing user data.
     * @param passwordEncoder The password encoder for encrypting user passwords.
     */
	public UserServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
     * Creates a new user with the provided user information.
     * Validates the user request and saves the user entity to the database.
     *
     * @param userRequest The user information to be used for creating the user.
     * @throws BusinessRuleException If validation rules are not met.
     */
	@Override
	public void createUser(UserRequest userRequest) {
		
		validate(userRequest);
		
		UserEntity entity = UserMapper.mapToEntity(userRequest);
		entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
		userRepository.save(entity);
	}

	/**
     * Validates the user information based on business rules.
     *
     * @param userRequest The user information to be validated.
     * @throws BusinessRuleException If validation rules are not met.
     */
	private void validate(UserRequest userRequest) {
		
		if (!userRequest.password().equals(userRequest.confirmedPassword()))
			throw new BusinessRuleException("errors.confirmed.password.match", HttpStatus.BAD_REQUEST);
		
		if(!userRequest.country().equals(Country.FRANCE))
			throw new BusinessRuleException("errors.not.french.resident", HttpStatus.BAD_REQUEST);
		
		if(userRequest.birthday().plusYears(18).isAfter(LocalDate.now()))
			throw new BusinessRuleException("errors.under.age", HttpStatus.BAD_REQUEST);
	}

	/**
     * Retrieves user information by user ID.
     *
     * @param userId The unique identifier of the user.
     * @return The user information if found.
     * @throws UserNotFoundException If the user with the specified ID is not found.
     */
	@Override
	public UserResponse getUserById(Long userId) {
		
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("errors.user.not.found", new String[] { userId.toString() }));
		
		return UserMapper.mapToDto(userEntity);
	}

	@Override
	public List<UserResponse> getUsersByCountry(Country country) {
		
		List<UserEntity> usersByCountry = userRepository.findByCountry(country);
        return usersByCountry.stream()
                .map(UserMapper::mapToDto)
                .collect(Collectors.toList());
	}

}
