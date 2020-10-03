package com.pathway.enrollment.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pathway.enrollment.dto.UserDTO;
import com.pathway.enrollment.model.User;
import com.pathway.enrollment.repository.UserRepository;
import com.pathway.enrollment.util.SecurityPasswordEncoder;

@Service
@Transactional
public class PathwayEnrollmentUserServiceImpl {
	
	private static final Logger log = LogManager.getLogger(PathwayEnrollmentUserServiceImpl.class);

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecurityPasswordEncoder securityPasswordEncoder;


	public UserDTO getUser(String userName)  {
		log.info("Inside the get User details service");
		User user = userRepository.findByUserName(userName);
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName(user.getUserName());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}
	
	public UserDTO addUser(UserDTO userDTO){
		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setPassword(securityPasswordEncoder.encodePassword(userDTO.getPassword()));
		log.info("Inside the add User details service");
		User userAdded = userRepository.save(user);
		UserDTO userDTOAdded = new UserDTO();
		userDTOAdded.setUserName(userAdded.getUserName());
		return userDTOAdded;
	}
}
