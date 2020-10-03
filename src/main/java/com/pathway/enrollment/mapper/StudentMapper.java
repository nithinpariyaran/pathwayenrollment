package com.pathway.enrollment.mapper;

import org.springframework.stereotype.Component;

import com.pathway.enrollment.dto.StudentDTO;
import com.pathway.enrollment.model.Student;
/**
 * The method will help to perform the mapping between model and DTO objects for students
 * @author Nithin
 *
 */
@Component
public class StudentMapper {

	
	/**
	 * The method is used to map the student DTO to student Model
	 * @param studentDTO
	 * @return Student Model
	 */
	public Student mapStudentDTOtoStudentModel(StudentDTO studentDTO) {
		Student student = new Student();
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setDivision(studentDTO.getDivision());
		student.setCountry(studentDTO.getCountry());
		return student;
	}
	
	/**
	 * The method is used to map the student DTO to student Model
	 * @param studentDTO
	 * @return Student Model
	 */
	public Student updateStudentDTOtoStudentModel(Student student ,StudentDTO studentDTO) {
		student.setId(studentDTO.getId());
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setDivision(studentDTO.getDivision());
		student.setCountry(studentDTO.getCountry());
		return student;
	}
	
	
	/**
	 * The method is used to map the student model to student DTO
	 * @param Student student
	 * @return StudentDTO studentDTO
	 */
	public StudentDTO mapStudentModeltoStudentDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(student.getId());
		studentDTO.setFirstName(student.getFirstName());
		studentDTO.setLastName(student.getLastName());
		studentDTO.setDivision(student.getDivision());
		studentDTO.setCountry(student.getCountry());
		return studentDTO;
	}
	
	
	

}
