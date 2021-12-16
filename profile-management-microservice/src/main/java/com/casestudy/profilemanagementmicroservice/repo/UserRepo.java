package com.casestudy.profilemanagementmicroservice.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.profilemanagementmicroservice.model.User;

public interface UserRepo extends MongoRepository<User,String> {

	Optional<User> findByUserName(String username);

	


	
	
	
}
