package com.example.demo.service;

 
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.users;
import com.example.demo.repository.UserRepo;
import com.example.demo.repository.UserRole;
import com.example.demo.types.RoleType; 

@Service
public class UsersService implements UserDetailsService{
	private final UserRepo userRepo;
	private final UserRole userRole;

	

	public UsersService(UserRepo userRepo, UserRole userRole) { 
		this.userRepo = userRepo;
		this.userRole = userRole;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		users user = userRepo.findByUserName(username);
		if (null == user){
			throw new UsernameNotFoundException("No user present with username: "+username);
		} else {
			List<RoleType> userRoles=userRole.findRoleByUserName(username);
			return new userService(user,userRoles);
		} 
	} 


}
