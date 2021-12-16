package com.casestudy.profilemanagementmicroservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.casestudy.profilemanagementmicroservice.model.User;
import com.casestudy.profilemanagementmicroservice.repo.UserRepo;
import com.casestudy.profilemanagementmicroservice.service.ProfileService;




@RunWith(SpringRunner.class)
@SpringBootTest
class ProfileManagementMicroserviceApplicationTests {

	@Autowired
	ProfileService service;
	
	@MockBean
	UserRepo repo;
	
	
	 
	
	

	@Test
	public void addUserTest() {
		User user = new User("6", "madurai","city",true,"user");
		when(repo.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	@Test
	public void greetTest()
	{
		String greet = "welcome to our home";
	   assertEquals(greet,service.greet());
		
	}
	

}