package com.pathway.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathway.enrollment.model.User;
/**
 * This repository will perform the database operation for user entity
 * @author Nithin
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUserName(String userName);
}