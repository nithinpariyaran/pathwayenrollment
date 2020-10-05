package com.pathway.enrollment.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pathway.enrollment.dto.UserDTO;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PathwayEnrollmentUserServiceImplTest {
	
	
	@Autowired
	private PathwayEnrollmentUserServiceImpl pathwayEnrollmentUserServiceImpl;
	
	
	@Test
	public void testAddUser() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("varsha");
		userDTO.setPassword("1234");
		UserDTO userDTOAdded = pathwayEnrollmentUserServiceImpl.addUser(userDTO);
		Assert.assertNotNull(userDTOAdded);
	}
	
	//@Test
	public void testgetUserByName() {
		pathwayEnrollmentUserServiceImpl.getUser("Nithin");
	}
	

	

}
	