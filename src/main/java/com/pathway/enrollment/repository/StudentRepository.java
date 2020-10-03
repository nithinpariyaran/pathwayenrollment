package com.pathway.enrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathway.enrollment.model.Student;
/**
 * This repository will perform the database operation for student entity
 * @author Nithin
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	List<Student> findByDivision(String divisionName);
	
}