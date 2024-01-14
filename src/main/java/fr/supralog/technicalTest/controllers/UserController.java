package fr.supralog.technicalTest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.supralog.technicalTest.common.enums.Country;
import fr.supralog.technicalTest.dto.request.UserRequest;
import fr.supralog.technicalTest.dto.response.UserResponse;
import fr.supralog.technicalTest.services.UserService;
import jakarta.validation.Valid;

/**
 * Controller class for handling user-related HTTP requests.
 * Manages endpoints for retrieving and creating user information.
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	/**
     * Constructs a new UserController with the specified UserService.
     *
     * @param userService The UserService to be used by the controller.
     */
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	/**
     * Retrieves user information by user ID.
     *
     * @param id The unique identifier of the user.
     * @return ResponseEntity containing the user information if found, or HttpStatus.NOT_FOUND if not found.
     */
	@GetMapping("{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

		UserResponse user = userService.getUserById(id);

		return new ResponseEntity<UserResponse>(user, HttpStatus.OK);
	}

	/**
     * Creates a new user with the provided userRequest.
     *
     * @param userRequest The user information to be used for creating the user.
     * @return ResponseEntity indicating successful user creation (HttpStatus.CREATED) or validation errors (HttpStatus.BAD_REQUEST).
     */
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequest userRequest) {

		this.userService.createUser(userRequest);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/**
	 * Retrieves a list of users based on the specified country.
	 *
	 * @param country The country for which to retrieve users. Defaults to "FRANCE" if not provided.
	 * @return ResponseEntity containing a list of user information if found, or HttpStatus.NO_CONTENT if no users match the criteria.
	 */
	@GetMapping
	public ResponseEntity<List<UserResponse>> getUsersByCountry(
			@RequestParam(name = "country", defaultValue = "FRANCE") Country country) {
		
		List<UserResponse> users = userService.getUsersByCountry(country);
		if (CollectionUtils.isEmpty(users))
			return new ResponseEntity<List<UserResponse>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
