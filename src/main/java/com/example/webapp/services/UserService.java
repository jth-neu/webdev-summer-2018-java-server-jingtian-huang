package com.example.webapp.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.models.User;
import com.example.webapp.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user) {
		Optional<User> data =
				repository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(data.isPresent()) {
			return data.get();
		} else {
			throw new NoSuchElementException("No matching user found.");
		}
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id) {
		Optional<User> data = repository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping(value="/api/user", params="username")
	public User findUserByUsername(@RequestParam(name="username") String username) {
		Optional<User> data = repository.findUserByUsername(username);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	//TODO: Use HttpSession to verify the login user
	@PostMapping("/api/register")
	public User register(@RequestBody User user) {  
		Optional<User> data = repository.findUserByUsername(user.getUsername());
		if(data.isPresent()) {
			throw new IllegalArgumentException("The Username already exist.");
		} else {
			repository.save(user);
			return user;
		}
	}

	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setUsername(newUser.getUsername());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			user.setDateOfBirth(newUser.getDateOfBirth());
			user.setPhone(newUser.getPhone());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		return null;
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User newUser) {
		Optional<User> data = repository.findUserByUsername(newUser.getUsername());
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setUsername(newUser.getUsername());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			user.setDateOfBirth(newUser.getDateOfBirth());
			user.setPhone(newUser.getPhone());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		return null;
	}
}
