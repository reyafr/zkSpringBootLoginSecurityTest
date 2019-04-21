package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.users; 

@Repository
public interface UserRepo extends JpaRepository<users, Long>{
	users findByUserName(String username);
}
