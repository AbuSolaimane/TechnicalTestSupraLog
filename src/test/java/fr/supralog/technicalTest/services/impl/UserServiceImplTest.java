package fr.supralog.technicalTest.services.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.supralog.technicalTest.common.enums.Country;
import fr.supralog.technicalTest.common.exceptions.BusinessRuleException;
import fr.supralog.technicalTest.common.exceptions.UserNotFoundException;
import fr.supralog.technicalTest.common.mappers.UserMapper;
import fr.supralog.technicalTest.domain.UserEntity;
import fr.supralog.technicalTest.dto.request.UserRequest;
import fr.supralog.technicalTest.dto.response.UserResponse;
import fr.supralog.technicalTest.repositories.UserRepository;

/**
 * Unit tests for {@link UserServiceImpl}.
 */
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for successful user creation.
     */
    @Test
    void createUser_ValidUserRequest_UserSavedSuccessfully() {
        // create user
        UserRequest userRequest = new UserRequest(
                "mostafa",
                "fadili",
                "mostafa@gmail.com",
                "password",
                "password",
                Country.FRANCE,
                LocalDate.parse("1999-10-30"),
                null,
                null,
                null
        );

        UserEntity mockedUserEntity = UserMapper.mapToEntity(userRequest);

        // Mocking
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenReturn(mockedUserEntity);
        
        //asert that it passes
        assertDoesNotThrow(() -> userService.createUser(userRequest));

        // Assert
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    /**
     * Test for password mismatch during user creation.
     */
    @Test
    void createUser_PasswordMismatch_ThrowsBusinessRuleException() {
        // create user
        UserRequest userRequest = new UserRequest(
        		"mostafa",
                "fadili",
                "mostafa@gmail.com",
                "password",
                "differentPassword",
                Country.FRANCE,
                LocalDate.parse("1990-01-01"),
                null,
                null,
                null
        );

        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> userService.createUser(userRequest));
        assertEquals("errors.confirmed.password.match", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    /**
     * Test for successful retrieval of a user by ID.
     */
    @Test
    void getUserById_UserExists_ReturnsUserResponse() {
        
        Long userId = 1L;
        UserEntity mockedUserEntity = new UserEntity();
        mockedUserEntity.setId(userId);
        mockedUserEntity.setFirstName("mostafa");
        mockedUserEntity.setLastName("fadili");
        mockedUserEntity.setEmail("mostafa@gmail.com");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockedUserEntity));

        UserResponse userResponse = userService.getUserById(userId);

        // Assert
        assertNotNull(userResponse);
        assertEquals(userId, userResponse.id());
        assertEquals("mostafa", userResponse.firstName());
        assertEquals("fadili", userResponse.lastName());
        assertEquals("mostafa@gmail.com", userResponse.email());

        // Verify that findById was called once
        verify(userRepository, times(1)).findById(userId);
    }

    /**
     * Test for user not found by ID.
     */
    @Test
    void getUserById_UserDoesNotExist_ThrowsUserNotFoundException() {
        
        Long userId = 1L;

        // Mocking
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
        assertEquals("errors.user.not.found", exception.getMessage());
        assertArrayEquals(new String[]{userId.toString()}, exception.getParams());

        // Verify that findById was called once
        verify(userRepository, times(1)).findById(userId);
    }

    /**
     * Test for successful retrieval of users by country.
     */
    @Test
    void getUsersByCountry_UsersExist_ReturnsListOfUserResponse() {

    	Country country = Country.FRANCE;
        List<UserEntity> mockedUsers = new ArrayList<>();
        mockedUsers.add(createMockedUserEntity(1L, "mostafa", "fadili", "mostafa@gmail.com"));
        mockedUsers.add(createMockedUserEntity(2L, "bilal", "fadili", "bilal@gmail.com"));

        // Mocking
        when(userRepository.findByCountry(any())).thenReturn(mockedUsers);

        List<UserResponse> userResponses = userService.getUsersByCountry(country);

        assertNotNull(userResponses);
        assertEquals(2, userResponses.size());

        // Verify that findByCountry was called once
        verify(userRepository, times(1)).findByCountry(country);
    }

    /**
     * Helper method to create a mocked {@link UserEntity}.
     */
    private UserEntity createMockedUserEntity(Long id, String firstName, String lastName, String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setEmail(email);
        userEntity.setCountry(Country.FRANCE);
        return userEntity;
    }
}
