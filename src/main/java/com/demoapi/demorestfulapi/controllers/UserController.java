package com.demoapi.demorestfulapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoapi.demorestfulapi.entities.User;
import com.demoapi.demorestfulapi.exceptions.ResourceNotFoundException;
import com.demoapi.demorestfulapi.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

@Autowired
private UserRepository userRepository;

	/**
	* Get all users list.
	* @return the list
	*/
	@GetMapping("/list")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	* Gets users by id.
	* @param userId the user id
	* @return the users by id
	* @throws ResourceNotFoundException the resource not found exception
	*/
	@GetMapping("/get/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on:" + userId));
		return ResponseEntity.ok().body(user);
	}

	/**
	* Create user user.
	* @param user the user
	* @return the user
	*/
	@PostMapping("/add")
	public User create(@Validated @RequestBody User user) {
		return userRepository.save(user);
	}

	/**
	* Update user response entity.
	* @param userId the user id
	* @param userDetails the user details
	* @return the response entity
	* @throws ResourceNotFoundException the resource not found exception
	*/
	@PutMapping("/update/{id}")
	public ResponseEntity<User> update(@PathVariable(value = "id") Long userId, @Validated @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on:" + userId));
		user.setPassword(userDetails.getPassword());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	/**
	* Delete user map.
	* @param userId the user id
	* @return the map
	* @throws Exception the exception
	*/
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Long userId) throws Exception {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on:" + userId));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	* Sign in
	* @param username
	* @param password
	* @return User
	*/
	@PostMapping("/signin")
	public ResponseEntity<User> signIn(@Validated @RequestBody User u) {
		User user = userRepository.findByUsernameAndPassword(u.getUsername(), u.getPassword());
		if (user == null) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(user);
	}
}