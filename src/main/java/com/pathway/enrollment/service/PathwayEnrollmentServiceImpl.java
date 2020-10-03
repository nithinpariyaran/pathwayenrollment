package com.pathway.enrollment.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pathway.enrollment.dto.StudentDTO;
import com.pathway.enrollment.exception.ResourceNotFoundException;
import com.pathway.enrollment.mapper.StudentMapper;
import com.pathway.enrollment.model.Student;
import com.pathway.enrollment.repository.StudentRepository;
/**
 * The method will help to perform the enrolment service for the students
 * @author Nithin
 *
 */
@Service
@Transactional
public class PathwayEnrollmentServiceImpl {
	
	private static final String NOT_FOUND = " not found";


	private static final String STUDENT_CONST = "Student ";


	private static final Logger log = LogManager.getLogger(PathwayEnrollmentServiceImpl.class);

	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentMapper studentMapper;

	/**
	 * The method is used to get all the students
	 * 
	 * @param studentDTO
	 * @return studentDTO
	 */
	public List<StudentDTO> getAllStudent() {
		log.info("Inside the get all students details service");
		List<Student> students = studentRepository.findAll();
		List<StudentDTO> studentDTOs = new ArrayList<>();
		for (Student student : students) {
			studentDTOs.add(studentMapper.mapStudentModeltoStudentDTO(student));
		}
		log.info("Total students details "+studentDTOs);
		return studentDTOs;

	}

	/**
	 * The method is used to add new student
	 * 
	 * @param studentDTO
	 * @return studentDTO
	 */
	public StudentDTO addStudent(StudentDTO studentDTO) {
		log.info("Inside the add student details service");
		Student student = studentRepository.save(studentMapper.mapStudentDTOtoStudentModel(studentDTO));
		StudentDTO studentDTOAdded = studentMapper.mapStudentModeltoStudentDTO(student);
		log.info("Added student details"+studentDTOAdded);
		return studentDTOAdded;

	}

	/**
	 * The method is used to update the student
	 * 
	 * @param studentDTO
	 * @return studentDTO
	 * @throws ResourceNotFoundException
	 */
	public StudentDTO updateStudent(StudentDTO studentDTO) throws ResourceNotFoundException {
		log.info("Inside the update student details service");
		Student student = studentRepository.findById(studentDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException(STUDENT_CONST + studentDTO.getId() + NOT_FOUND));
		Student updateStudent = studentMapper.updateStudentDTOtoStudentModel(student, studentDTO);
		studentRepository.save(updateStudent);
		StudentDTO studentDTOUpdated = studentMapper.mapStudentModeltoStudentDTO(updateStudent);
		log.info("Updated student details"+studentDTOUpdated);
		return studentDTOUpdated;
	}

	/**
	 * The method is used to delete the student
	 * 
	 * @param studentDTO
	 * @return status
	 * @throws ResourceNotFoundException
	 */
	public String deleteStudent(int studentId) throws ResourceNotFoundException {
		log.info("Inside the delete student details service");
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException(STUDENT_CONST + studentId + NOT_FOUND));
		studentRepository.delete(student);
		log.info("Student deleted succesully "+student);
		return "Deleted";
		

	}

	/**
	 * The method is used to get individual the student
	 * 
	 * @param studentDTO
	 * @return studentDTO
	 * @throws ResourceNotFoundException
	 */
	public StudentDTO getStudent(int studentId) throws ResourceNotFoundException {
		log.info("Inside the get Individual student details service");
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException(STUDENT_CONST + studentId + NOT_FOUND));
		StudentDTO studentDTO = studentMapper.mapStudentModeltoStudentDTO(student);
		log.info("Individual student details"+studentDTO);
		return studentDTO;
	}
	
	/**
	 * The method is used to get individual the student
	 * 
	 * @param studentDTO
	 * @return studentDTO
	 * @throws ResourceNotFoundException
	 */
	public List<StudentDTO> getStudentByDivision(String division) throws ResourceNotFoundException {
		log.info("Inside the get students based on division");
		List<StudentDTO> studentDTOs = new ArrayList<>();
		List<Student> students = studentRepository.findByDivision(division);
		if(students.isEmpty()) {
			throw new ResourceNotFoundException("No student available in the Class"+division);
		}else {
			for (Student student : students) {
				studentDTOs.add(studentMapper.mapStudentModeltoStudentDTO(student));
			}	
		}
		return studentDTOs;
	}

}
