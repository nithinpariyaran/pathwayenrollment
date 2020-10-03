package com.pathway.enrollment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pathway.enrollment.dto.StudentDTO;
import com.pathway.enrollment.dto.UserDTO;
import com.pathway.enrollment.exception.ResourceNotFoundException;
import com.pathway.enrollment.service.PathwayEnrollmentServiceImpl;
import com.pathway.enrollment.service.PathwayEnrollmentUserServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class PathwayEnrollmentController {

	private static final Logger log = LogManager.getLogger(PathwayEnrollmentController.class);

	@Autowired
	private PathwayEnrollmentServiceImpl pathwayEnrollmentService;

	@Autowired
	private PathwayEnrollmentUserServiceImpl pathwayEnrollmentUserService;

	/**
	 * The method is used to get all students
	 * 
	 * @return List Students
	 */
	@ApiOperation(value = "Fetches all students in the database.", response = StudentDTO.class)
	@GetMapping("/api/v1/students") // GET Method for reading operation
	public List<StudentDTO> getAllStudents() {
		log.info("Inside the get all students details");
		return pathwayEnrollmentService.getAllStudent();
	}

	/**
	 * The method will add a single student
	 * 
	 * @param student
	 * @return Student
	 */
	@ApiOperation(value = "Handles the creation of a Student.", response = StudentDTO.class)
	@PostMapping("/api/v1/addStudent")
	public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
		log.info("Inside the add Student details");
		return pathwayEnrollmentService.addStudent(studentDTO);
	}

	/**
	 * The method is used to get a single student by ID
	 * 
	 * @param studentId
	 * @return Student
	 * @throws ResourceNotFoundException
	 */
	@ApiOperation(value = "Fetches a single Student by its id.", response = StudentDTO.class)
	@GetMapping("/api/v1/getStudent") // GET Method for Read operation
	public ResponseEntity<StudentDTO> getStudentById(@RequestParam(value = "id") int studentId)
			throws ResourceNotFoundException {
		log.info("Inside the get Student details by ID" + studentId);
		final StudentDTO studentDTO = pathwayEnrollmentService.getStudent(studentId);
		return ResponseEntity.ok().body(studentDTO);
	}

	/**
	 * The method is used to get list of students by class name
	 * 
	 * @param studentId
	 * @return Student
	 * @throws ResourceNotFoundException
	 */
	@ApiOperation(value = "Fetches a list of  Student by its class.", response = StudentDTO.class)
	@GetMapping("/api/v1/getStudentForClass") // GET Method for Read operation
	public ResponseEntity<List<StudentDTO>> getStudentByClassName(@RequestParam(value = "className") String className)
			throws ResourceNotFoundException {
		log.info("Inside the get Student details by class name" + className);
		final List<StudentDTO> studentDTOs = pathwayEnrollmentService.getStudentByDivision(className);
		return ResponseEntity.ok().body(studentDTOs);
	}

	/**
	 * The method will update the student details.
	 * 
	 * @param studentDTO
	 * @return Student
	 * @throws ResourceNotFoundException
	 */
	@ApiOperation(value = "Handles the editing of a single student's details.", response = StudentDTO.class)
	@PostMapping("/api/v1/updateStudent") // PUT Method for Update operation
	public ResponseEntity<StudentDTO> updateStudent(@Valid @RequestBody StudentDTO studentDTO)
			throws ResourceNotFoundException {
		log.info("Inside the update Student details");
		final StudentDTO updateStudentDTO = pathwayEnrollmentService.updateStudent(studentDTO);
		return ResponseEntity.ok(updateStudentDTO);
	}

	/**
	 * The method will delete the student details
	 * 
	 * @param studentId
	 * @return Success/Failure
	 * @throws ResourceNotFoundException
	 */
	@ApiOperation(value = "Handles the deletion of a single student by its id.", response = StudentDTO.class)
	@DeleteMapping("/api/v1/deleteStudent/{id}") // DELETE Method for Delete operation
	public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") int studentId) throws ResourceNotFoundException {
		log.info("Inside the delete Student details for the id " + studentId);
		pathwayEnrollmentService.deleteStudent(studentId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 * The method will add a single student
	 * 
	 * @param student
	 * @return Student
	 */
	@ApiOperation(value = "Handles the creation of a User.", response = UserDTO.class)
	@PostMapping("/api/v1/addUser")
	public UserDTO addAdmin(@RequestBody UserDTO userDTO) {
		log.info("Inside the add User details");
		return pathwayEnrollmentUserService.addUser(userDTO);
	}
	
	/**
	 * The method will add a single student
	 * 
	 * @param student
	 * @return Student
	 */
	@ApiOperation(value = "Handles the Health check.", response = UserDTO.class)
	@GetMapping("/api/v1/healthCheck")
	public String healthCheck() {
		log.info("Inside the healthCheck method");
		return "Healthy";
	}

}
