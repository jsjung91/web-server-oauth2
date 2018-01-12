package com.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.ComputeModel;
import com.api.repository.ComputeRepository;

@RestController
@RequestMapping(value="/rest/v1/compute")
public class ComputeController {
	
	@Autowired
	private ComputeRepository computeRepo;
	
	//Get all
	@GetMapping("/all")
	public List<ComputeModel> getAllInstances() {
		return computeRepo.findAll();
	}
	
	@PostMapping("/create")
	public ComputeModel createInstance(@Valid @RequestBody ComputeModel createInstance) {
		return computeRepo.save(createInstance);
	}
	
	//Delete a Member
	@DeleteMapping("/delete/{i_id}")
	public ResponseEntity<ComputeModel> deleteMember(@PathVariable(value="i_id") int i_id) {
		ComputeModel compute = computeRepo.findOne(i_id);
		
		if(compute == null) {
			return ResponseEntity.notFound().build();
		}
		
		computeRepo.delete(compute);
		
		return ResponseEntity.ok().build();
	}
}
