package com.casestudy.profilemanagementmicroservice.resource;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.profilemanagementmicroservice.exception.CatalogRequestException;
import com.casestudy.profilemanagementmicroservice.model.User;
import com.casestudy.profilemanagementmicroservice.repo.UserRepo;
import com.casestudy.profilemanagementmicroservice.service.GroupUserDetailsService;
import com.casestudy.profilemanagementmicroservice.service.ProfileService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	ProfileService service;

	@Autowired
	private GroupUserDetailsService userDetailsService;
	
	@Autowired
	UserRepo repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping("/home")
    public void welcomeMsg()
    {
    	service.greet();
    }

    @PostMapping("/join")
    public String joinMyGroup(@RequestBody User user) {
      service.addUser(user);
        return "Hi " + user.getUserName() + " welcome to group !";
    }
   

   

    @GetMapping("/allUsers")
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> loadMyUsers() {
        return service.getUsers();
    }

   
   
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
	 public ResponseEntity<Object> updateuser(@PathVariable String id,  @RequestBody User user )
	 {
		 return service.updateuser(id,user);
	 	
	 }
    
    @DeleteMapping("/delete/{id}")
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteMyUserById(@PathVariable("id") String id)
    {
    	return service.deleteById(id);
    	
    }
    @GetMapping("/allusers/{id}")
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 public Optional<User> getuser(@PathVariable String id)
	 		throws CatalogRequestException
	 {
		 return service.getuser(id);
		
	 }
    
    private List<String> getRolesByLoggedInUser(Principal principal) {
        String roles = getLoggedInUser(principal).getRoles();
        List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
        if (assignRoles.contains("ROLE_ADMIN")) {
            return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
        
        private User getLoggedInUser(Principal principal) {
            return repository.findByUserName(principal.getName()).get();
        }

   
    
    
    
}