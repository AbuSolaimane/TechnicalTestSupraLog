package fr.supralog.technicalTest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.supralog.technicalTest.dto.request.UserRequest;
import fr.supralog.technicalTest.dto.response.UserResponse;
import fr.supralog.technicalTest.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

		UserResponse user = userService.getUserById(id);

		return new ResponseEntity<UserResponse>(user, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest) {

		this.userService.createUser(userRequest);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
