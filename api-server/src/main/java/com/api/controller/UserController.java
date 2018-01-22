package com.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.UserModel;
import com.api.repository.UserRepository;
import com.api.util.ApiResponse;
import com.api.util.PasswordEncode;

@RestController
@RequestMapping("/rest/v1/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	ComputeController computeControll;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	PasswordEncode passwordEncode = new PasswordEncode(passwordEncoder);

	//Get All
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllUsers() {
		List<UserModel> user = userRepository.findAll();

		if(user == null) {
			return new ApiResponse().errorSend(HttpStatus.NOT_FOUND, "Data is not Found");
		}

		return new ApiResponse(user).send(HttpStatus.OK);
	}

	//Login
	@GetMapping("/login/{m_email:.+}/{m_pwd}")
	public ResponseEntity<ApiResponse> login(@PathVariable(value="m_email") String m_email,
			@PathVariable(value="m_pwd") String m_pwd) {
		UserModel user = userRepository.findByMail(m_email);

		if(user == null || !passwordEncode.matches(m_pwd, user.getM_pwd())) {
			return new ApiResponse().errorSend(HttpStatus.NOT_FOUND, "Data is Invalid");
		}
		
		computeControll.isLoginMail = m_email;
		
		UserModel loginData = new UserModel();
		
		loginData.setM_id(user.getM_id());
		loginData.setM_lastname(user.getM_lastname());
		loginData.setM_firstname(user.getM_firstname());
		loginData.setM_country(user.getM_country());
		loginData.setM_email(user.getM_email());
		loginData.setM_pwd(user.getM_pwd());
		loginData.setEnabled(user.isEnabled());

		return new ApiResponse(loginData).send(HttpStatus.OK);

	}
	
	//Logout
	@GetMapping("/logout")
	public ResponseEntity<ApiResponse> logout() {

		if(computeControll.isLoginMail != "") {
			computeControll.isLoginMail = "";
		}
		
		return new ApiResponse().send(HttpStatus.OK);

	}

	//Create User
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserModel createUser) {

		if(createUser == null) {
			return new ApiResponse().errorSend(HttpStatus.FAILED_DEPENDENCY, "Failed Create User");	
		}
		
		String endodePwd = passwordEncode.encode(createUser.getM_pwd());

		createUser.setM_pwd(endodePwd);
		createUser.setEnabled(true);
		
		userRepository.save(createUser);
		
		return new ApiResponse(createUser).send(HttpStatus.OK);
	}

	//Get User By Id
	@GetMapping("/getbyid/{m_id}")
	public ResponseEntity<ApiResponse> getUserById(@PathVariable(value="m_id") int m_id) {
		UserModel user = userRepository.findOne(m_id);

		if(user == null) {
			return new ApiResponse().errorSend(HttpStatus.NOT_FOUND, "Data is Not Found");	
		}

		return new ApiResponse(user).send(HttpStatus.OK);
	}

	//Get User By Email
	@GetMapping("/getbymail/{m_email:.+}") //Email 주소 짤리는 문제로인한 :.+ 추가
	public ResponseEntity<ApiResponse> getUserBymail(@PathVariable(value="m_email") String m_email) {

		UserModel user = userRepository.findByMail(m_email);

		if(user == null) {
			return new ApiResponse().errorSend(HttpStatus.NOT_FOUND, "Data is Not Found");
		}

		return new ApiResponse(user).send(HttpStatus.OK);
	}

	//Update a User
	@PutMapping("/update/{m_id}")
	public ResponseEntity<ApiResponse> updateUser(@PathVariable(value="m_id") int m_id,
			@Valid @RequestBody UserModel userDetails) {
		
		UserModel user = userRepository.findOne(m_id);

		if(user == null) {
			return new ApiResponse().errorSend(HttpStatus.NOT_MODIFIED, "Data is Not Modified");
		}

		user.setM_lastname(userDetails.getM_lastname());
		user.setM_firstname(userDetails.getM_firstname());
		user.setM_country(userDetails.getM_country());

		UserModel updateUser = userRepository.save(user);

		return new ApiResponse(updateUser).send(HttpStatus.OK);
	}

	//Delete a User
	@DeleteMapping("/delete/{m_id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value="m_id") int m_id) {
		
		UserModel user = userRepository.findOne(m_id);

		if(user == null) {
			return new ApiResponse().errorSend(HttpStatus.FAILED_DEPENDENCY, "Failed");
		}

		userRepository.delete(user);

		return new ApiResponse(user).send(HttpStatus.OK);
	}

}
