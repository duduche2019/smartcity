package com.smartcity.smartcity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartcity.smartcity.DAO.UserDAO;
import com.smartcity.smartcity.exception.UserNotFoundException;
import com.smartcity.smartcity.model.User;
import com.smartcity.smartcity.model.enums.Access;

@RestController
public class UserController {

	@Autowired
	UserDAO userDAO;

	/**
	 * Request a new user creation.
	 * 
	 * @param user User informations for the new user creation.
	 * @return URI of the user newly created, or 409 (Conflict) if User login
	 *         already exists.
	 */
	@PostMapping(value = "/createUser")
	public User addUser(@RequestBody User user) {
		User createdUser = userDAO.save(user);
		return createdUser;
	}

	/**
	 * Update an existing user.
	 * 
	 * @param user User informations to put in the actual user.
	 * @return 200 if correctly updated, 404 if no such user userId found.
	 */
	@PutMapping(value = "/updateUser")
	public User updateUser(@RequestBody User user) {
		User updatedUser = userDAO.save(user);
		return updatedUser;
	}

	/**
	 * Get a specific user.
	 * 
	 * @param userId userId of the user wished.
	 * @return The user wished, or 404 if not found.
	 */
	@GetMapping(value = "/getUserById/{userId}")
	public User getUserById(@PathVariable int userId) throws UserNotFoundException {

		User userFound = userDAO.findOne(userId);

		if (userFound == null) {

			throw new UserNotFoundException();
		}
		return userFound;

	}

	/**
	 * Get a specific user.
	 * 
	 * @param login userId of the user wished.
	 * @return The user wished, or 404 if not found.
	 */
	@GetMapping(value = "/getAllUserByLogin/{login}")
	public User getUserByLogin(@PathVariable String login) {
		User UsersFound = userDAO.getUserByLogin(login);
		return UsersFound;
	}

	/**
	 * Get a specific user.
	 * 
	 * @param
	 * 
	 * @return All users, or 404 if not found.
	 */
	@GetMapping(value = "/getAllUsers")
	public List<User> getAllUsers() {
		List<User> UsersFound = userDAO.findAll();
		return UsersFound;
	}

	/**
	 * Delete a specific user.
	 * 
	 * @param userId
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteByUserId/{userId}")
	public void deleteUserById(@PathVariable int userId) {
		userDAO.deleteByUserId(userId);
	}

	/**
	 * Delete a all users By Access.
	 * 
	 * @param access
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteAllUsersByAccess/{access}")
	public void deleteAllUsers(@PathVariable Access access) {
		userDAO.deleteUsersByAccess(access);
	}

	/**
	 * Delete a All users.
	 * 
	 * @param
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteAllUsers")
	public void deleteAllUsers() {
		userDAO.deleteAll();
	}

}
