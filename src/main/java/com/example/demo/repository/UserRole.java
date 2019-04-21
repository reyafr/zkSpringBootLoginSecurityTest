package com.example.demo.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.usersRoles;
import com.example.demo.types.RoleType;
 

@Repository
public interface UserRole extends CrudRepository<usersRoles, Long>{
	@Query("select a.role from usersRoles a, users b where b.userName=?1 ")
    List<RoleType> findRoleByUserName(String username);

}
