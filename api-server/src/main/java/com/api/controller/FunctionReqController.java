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

import com.api.model.FunctionReqModel;
import com.api.model.UserModel;
import com.api.repository.FunctionReqRepository;
import com.api.repository.UserRepository;
import com.api.util.ApiResponse;

@RestController
@RequestMapping(value = "/rest/v1/funcreq")
public class FunctionReqController {

	@Autowired
	private FunctionReqRepository functionreqRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserController userControl;

	// Get all
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAlFunctionReqs() {

		UserModel user = userRepo.findByMail(userControl.isLoginMailID);

		if (user == null) {
			return new ApiResponse().errorSend(HttpStatus.FAILED_DEPENDENCY, "Failed");
		} else if (userControl.isLoginMailID == "") {
			return new ApiResponse().errorSend(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
		}

		return new ApiResponse(user.getFunctionReqLists()).send(HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createInstance(@Valid @RequestBody FunctionReqModel createFunctionReq) {

		if (createFunctionReq == null) {
			return new ApiResponse().errorSend(HttpStatus.FAILED_DEPENDENCY, "Failed");
		} else if (userControl.isLoginMailID == "") {
			return new ApiResponse().errorSend(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
		}

		createFunctionReq.setUserModel(userRepo.findByMail(userControl.isLoginMailID));

		functionreqRepo.save(createFunctionReq);

		return new ApiResponse(createFunctionReq).send(HttpStatus.OK);
	}

	// Delete a Member
	@DeleteMapping("/delete/{f_id}")
	public ResponseEntity<ApiResponse> deleteMember(@PathVariable(value = "f_id") int f_id) {
		FunctionReqModel delFunctionReq = functionreqRepo.findOne(f_id);

		if (delFunctionReq == null) {
			return new ApiResponse().errorSend(HttpStatus.FAILED_DEPENDENCY, "Failed");
		}

		functionreqRepo.delete(delFunctionReq);

		return new ApiResponse(delFunctionReq).send(HttpStatus.OK);
	}

}