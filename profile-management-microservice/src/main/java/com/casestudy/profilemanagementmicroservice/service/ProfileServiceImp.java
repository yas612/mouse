package com.casestudy.profilemanagementmicroservice.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.casestudy.profilemanagementmicroservice.exception.CatalogRequestException;
import com.casestudy.profilemanagementmicroservice.model.User;
import com.casestudy.profilemanagementmicroservice.repo.UserRepo;
import com.casestudy.profilemanagementmicroservice.resource.UserConstant;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class ProfileServiceImp implements ProfileService {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepo repository;

	@Override
	public User addUser(User user) {
		user.setRoles(UserConstant.DEFAULT_ROLE);
		String encryptedPwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPwd);
		 
		System.out.println("Hi "+user.getUserName()+" welcome to group!");
		return repository.save(user);
	}

	

	@Override
	 @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> getUsers() {
	
		List<User> users = repository.findAll();
		System.out.println("retrieved all users : " + users);
		return users;
	}
	
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Object> updateuser(String id, User user) {
		
		
		boolean isUserExist=repository.existsById(id);
		 if(isUserExist) {
		 	repository.save(user);
		    	return new ResponseEntity<Object>("user Updated Successfully with id "+id,HttpStatus.OK);
		 }
		 else
		 {
			 throw new CatalogRequestException("CAN NOT UPDATE AS USER NOT FOUND WITH THIS ID ::");
		 }
	}
		

	


	 @Secured("ROLE_ADMIN")
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Object> deleteById(String id)
	{
		boolean isUserExist=repository.existsById(id);
		 if(isUserExist) {
			 repository.deleteById(id);
			 return new ResponseEntity<Object>("user deleted with id "+id,HttpStatus.OK);
		 }
		 else
		 {
		 	throw new CatalogRequestException("CAN NOT DELETE AS USER NOT FOUND WITH THIS ID ::");
		 }
	}

	@Override
	 @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Optional<User> getuser(String id) {
		return Optional.of(repository.findById(id)
				 .orElseThrow( () -> new CatalogRequestException("CUSTOMER NOT FOUND WITH THIS ID ::")));
		
	}



	@Override
	public User getLoggedInUser(Principal principal) {
		return repository.findByUserName(principal.getName()).get();
		
	}
	@Override
	public String greet()
	{
		return "welcome to our home";
	}



	
}
