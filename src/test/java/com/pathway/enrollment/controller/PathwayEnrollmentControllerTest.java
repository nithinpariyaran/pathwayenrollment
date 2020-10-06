package com.pathway.enrollment.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.pathway.enrollment.PathwayEnrollmentProgramApplication;
import com.pathway.enrollment.dto.StudentDTO;
import com.pathway.enrollment.dto.UserDTO;
import com.pathway.enrollment.exception.ResourceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PathwayEnrollmentProgramApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PathwayEnrollmentControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PathwayEnrollmentController pathwayEnrollmentController;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port + "/api/v1";
	}

	@Test
	public void testGetAllStudents() {
		HttpHeaders requestHeaders = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, requestHeaders);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/students", HttpMethod.GET, entity,
				String.class);
		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testAddStudent() {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setFirstName("HelloFname");
		studentDTO.setLastName("HelloLName");
		studentDTO.setDivision("3A");
		studentDTO.setCountry("IND");
		ResponseEntity<StudentDTO> postResponse = restTemplate.postForEntity(getRootUrl() + "/addStudent", studentDTO,
				StudentDTO.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateStudent() throws ResourceNotFoundException {
		StudentDTO studentDTO =addStudent();
		studentDTO.setLastName("UpdatedLastName");
		ResponseEntity<StudentDTO> responseEntity =  pathwayEnrollmentController.updateStudent(studentDTO);
		Assert.assertNotNull(responseEntity.getBody());
	}

	@Test
	public void testDeleteStudent() throws ResourceNotFoundException {
		StudentDTO studentDTO =addStudent();
		pathwayEnrollmentController.deleteStudent(studentDTO);

	}

	@Test
	public void testGetStudentById() throws ResourceNotFoundException {
		StudentDTO studentDTO =addStudent();
		ResponseEntity<StudentDTO> responseEntity = pathwayEnrollmentController.getStudentById(studentDTO.getId());
		Assert.assertNotNull(responseEntity.getBody());

	}

	@Test
	public void testGetStudentByClassName() throws ResourceNotFoundException {
		ResponseEntity<List<StudentDTO>> responseEntity = pathwayEnrollmentController.getStudentByClassName("3A");
		Assert.assertNotNull(responseEntity.getBody());

	}

	private StudentDTO addStudent() {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setFirstName("TestFName");
		studentDTO.setLastName("TestLName");
		studentDTO.setCountry("HK");
		studentDTO.setDivision("2");
		StudentDTO studentDTOAdded = pathwayEnrollmentController.addStudent(studentDTO);
		return studentDTOAdded;
	}
}
