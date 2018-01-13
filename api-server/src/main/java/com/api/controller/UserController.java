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
import com.api.util.PasswordEncode;

@RestController
@RequestMapping("/rest/v1/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	PasswordEncode passwordEncode = new PasswordEncode(passwordEncoder);
	
	//Get All
	@GetMapping("/all")
	public List<UserModel> getAllUsers() {
		return userRepository.findAll();
	}
	
	//Login
	@GetMapping("/login/{m_email:.+}/{m_pwd}")
	public ResponseEntity<UserModel> login(@PathVariable(value="m_email") String m_email,
											@PathVariable(value="m_pwd") String m_pwd) {
		UserModel user = userRepository.findByMail(m_email);
		
		if(user == null || !passwordEncode.matches(m_pwd, user.getM_pwd())) {
			return ResponseEntity.notFound().build();
		}
		
//		return ResponseEntity.ok().body(user);
		return new ResponseEntity<UserModel>(user, HttpStatus.OK);
		
	}
	
	//Create User
	@PostMapping("/create")
	public UserModel createUser(@Valid @RequestBody UserModel createUser) {
		
		String endodePwd = passwordEncode.encode(createUser.getM_pwd());
		
		createUser.setM_pwd(endodePwd);
		
		return userRepository.save(createUser);
	}
	
	//Get User By Id
	@GetMapping("/getbyid/{m_id}")
	public ResponseEntity<UserModel> getUserById(@PathVariable(value="m_id") int m_id) {
		UserModel user = userRepository.findOne(m_id);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(user);
	}
	
	//Get User By Email
	@GetMapping("/getbymail/{m_email:.+}") //Email 주소 짤리는 문제로인한 :.+ 추가
	public ResponseEntity<UserModel> getUserBymail(@PathVariable(value="m_email") String m_email) {
		
		UserModel user = userRepository.findByMail(m_email);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(user);
	}
	
	//Update a User
	@PutMapping("/update/{m_id}")
	public ResponseEntity<UserModel> updateUser(@PathVariable(value="m_id") int m_id,
													@Valid @RequestBody UserModel userDetails) {
		UserModel user = userRepository.findOne(m_id);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		user.setM_lastname(userDetails.getM_lastname());
		user.setM_firstname(userDetails.getM_firstname());
		user.setM_country(userDetails.getM_country());
		
		UserModel updateUser = userRepository.save(user);
		
		return ResponseEntity.ok(updateUser);
	}
	
	//Delete a User
	@DeleteMapping("/delete/{m_id}")
	public ResponseEntity<UserModel> deleteUser(@PathVariable(value="m_id") int m_id) {
		UserModel user = userRepository.findOne(m_id);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		userRepository.delete(user);
		
		return ResponseEntity.ok().build();
	}

}
