package com.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.api.model.ComputeModel;
import com.api.repository.ComputeRepository;
import com.api.repository.UserRepository;
import com.api.util.ApiResponse;

@RestController
@RequestMapping(value="/rest/v1/compute")
public class ComputeController {
	
	@Autowired
	private ComputeRepository computeRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private String redirectUrl = "http://localhost:8080/rest/v1";
	
	public String isLoginMail = "";
	
	
	//Get all
	@GetMapping("/all")
	public RedirectView getAllInstances() {
//		return computeRepo.findAll();
		
		return new RedirectView(redirectUrl+"/user/getbymail/"+isLoginMail);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createInstance(@Valid @RequestBody ComputeModel createInstance) {
		
		if(createInstance == null) {
			return new ApiResponse().errorSend(HttpStatus.FAILED_DEPENDENCY, "Failed");	
		}else if(isLoginMail == "") {
			return new ApiResponse().errorSend(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
		}
		
		createInstance.setUserModel(userRepo.findByMail(isLoginMail));
		
		computeRepo.save(createInstance);
		
		return new ApiResponse(createInstance).send(HttpStatus.OK);
	}
	
	//Delete a Member
	@DeleteMapping("/delete/{i_id}")
	public ResponseEntity<ApiResponse> deleteMember(@PathVariable(value="i_id") int i_id) {
		ComputeModel delCompute = computeRepo.findOne(i_id);
		
		if(delCompute == null) {
			return new ApiResponse().errorSend(HttpStatus.FAILED_DEPENDENCY, "Failed");
		}
		
		computeRepo.delete(delCompute);
		
		return new ApiResponse(delCompute).send(HttpStatus.OK);
	}
}
