package com.pathway.enrollment.service;

import org.junit.Assert;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pathway.enrollment.dto.StudentDTO;
import com.pathway.enrollment.exception.ResourceNotFoundException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PathwayEnrollmentStudentServiceImplTest {
	
	private static final Logger log = LogManager.getLogger(PathwayEnrollmentStudentServiceImplTest.class);
	
	@Autowired
	private PathwayEnrollmentServiceImpl pathwayEnrollmentServiceImpl;
	
	@Test
	public void testGetAllStudents() {
		List<StudentDTO> studentDTOs = pathwayEnrollmentServiceImpl.getAllStudent();
		log.info("StudentDTOs " + studentDTOs.size());
		Assert.assertFalse(studentDTOs.isEmpty());
	}
	
	@Test
	public void testAddStudent() {
		StudentDTO studentDTOAdded = addStudent();
		log.info("studentDTOAdded  " + studentDTOAdded);
		Assert.assertNotNull(studentDTOAdded);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateNonExistentStudent() throws ResourceNotFoundException{
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(2);
		studentDTO.setFirstName("TestFName");
		studentDTO.setLastName("TestLName");
		studentDTO.setCountry("SGP");
		studentDTO.setDivision("2");
		pathwayEnrollmentServiceImpl.updateStudent(studentDTO);
	}

	
	@Test
	public void testUpdateStudent() throws ResourceNotFoundException{
		StudentDTO studentDTO = addStudent();
		studentDTO.setFirstName("UpdatedStudentName");
		StudentDTO studentDTOUpdated = pathwayEnrollmentServiceImpl.updateStudent(studentDTO);
		log.info("studentDTOUpdated  " + studentDTOUpdated);
		Assert.assertNotNull(studentDTOUpdated);
		
	}
	
//	@Test(expected = ResourceNotFoundException.class)
//	public void testDeleteNonExistentStudent() throws ResourceNotFoundException{
//		StudentDTO studentDTO = new StudentDTO();
//		studentDTO.setId(2);
//		studentDTO.setFirstName("TestFName");
//		studentDTO.setLastName("TestLName");
//		studentDTO.setCountry("SGP");
//		studentDTO.setDivision("2");
//		pathwayEnrollmentServiceImpl.deleteStudent(2); 
//	}

	@Test
	public void testDeleteStudent() throws ResourceNotFoundException{
		StudentDTO studentDTO = addStudent();
		String status = pathwayEnrollmentServiceImpl.deleteStudent(studentDTO.getId());
		Assert.assertEquals("Deleted", status);
		
	}
	
	@Test
	public void testGetStudentByClassName() throws ResourceNotFoundException {
		StudentDTO studentDTO = addStudent();
		List<StudentDTO> studentDTOs = pathwayEnrollmentServiceImpl.getStudentByDivision(studentDTO.getDivision());
		Assert.assertNotNull(studentDTOs);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testNonExistingStudentByClassName() throws ResourceNotFoundException {
		pathwayEnrollmentServiceImpl.getStudentByDivision("test");
		
	}
	
	@Test
	public void testGetStudentById() throws ResourceNotFoundException {
		StudentDTO studentDTO = addStudent();
		StudentDTO studentDTORetrieved = pathwayEnrollmentServiceImpl.getStudent(studentDTO.getId());
		Assert.assertNotNull(studentDTORetrieved);
	}


	private StudentDTO addStudent() {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setFirstName("TestFName");
		studentDTO.setLastName("TestLName");
		studentDTO.setCountry("HK");
		studentDTO.setDivision("2");
		StudentDTO studentDTOAdded = pathwayEnrollmentServiceImpl.addStudent(studentDTO);
		return studentDTOAdded;
	}


}
