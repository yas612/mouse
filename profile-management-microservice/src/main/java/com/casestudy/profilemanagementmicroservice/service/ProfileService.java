package com.casestudy.profilemanagementmicroservice.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.casestudy.profilemanagementmicroservice.model.User;


public interface ProfileService {
	
public User addUser(User user);
	
	public List<User> getUsers();
	
	
	
	public ResponseEntity<Object> deleteById(String id);

	public ResponseEntity<Object> updateuser(String id, User user);

	public Optional<User> getuser(String id);
	
	User getLoggedInUser(Principal principal);
	
	public String greet();

	 
	
		

}
