package com.multidb.db3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.multidb.db3.models.User3;
import com.multidb.db3.repositories.User3Repository;

@RestController // This annotation is used to define a controller that should include all the methods which are related to web requests.
public class User3Controller {

	@Autowired // This annotation is used to autowire bean on the setter method, constructor, a property or methods with arbitrary names and/or multiple arguments.
	private User3Repository userRepository; // This is an interface that allows you to perform various operations involving User3 objects. It gets these operations by extending one of the repository interfaces provided by Spring Data Commons.
	
	@PostMapping("/savedb3") // This annotation maps HTTP POST requests onto specific handler methods. It is a composed annotation that acts as a shortcut for @RequestMapping(method = POST).
	public String saved(@RequestBody User3 user) // This method is used to handle the HTTP POST request matched with given URI expression and save the user into the database.
	{
		userRepository.save(new User3(user.getName())); // Save the given entity.
		return "User saved in Database 3"; // Return a confirmation message.
	}
	
	@GetMapping("/getalldb3") // This annotation maps HTTP GET requests onto specific handler methods. It is a composed annotation that acts as a shortcut for @RequestMapping(method = GET).
	public List<User3> getalldb1() // This method is used to handle the HTTP GET request matched with given URI expression and return all users from the database.
	{
		return userRepository.findAll(); // Returns all instances of the type User3.
	}
	
}

