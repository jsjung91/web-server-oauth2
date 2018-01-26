package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.model.ComputeModel;
import com.api.repository.ComputeRepository;

@Controller
@RequestMapping(value="/rest/v1/response")
public class ResponseController {

	@Autowired
	private ComputeRepository computeRepo;

	//Status Complete	
	@RequestMapping(value="/mail/{teamname}")
	public String statusOk(@PathVariable(value="teamname") String teamname){
		ComputeModel stausCompute = computeRepo.findByTeamname(teamname);

		if(stausCompute == null){

			return "mail/mailError";
		}

		stausCompute.setI_enable(true);

		computeRepo.save(stausCompute);

		return "mail/mailSuccess";
	}

}
