package com.api.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.model.MailBodyContent;
import com.api.model.MailRequest;
import com.api.service.MailService;
import com.api.util.ApiResponse;

@RestController
@RequestMapping(value="/rest/v1/mail")
@PropertySource("classpath:application.properties")
public class MailController {

	@Autowired(required = true)
	private MailService service;
	
	@Value("${inlineImage}")
	String templateMailBodyImageVal;
	InputStreamSource imageSource = null;

	@RequestMapping("/send")
	public ResponseEntity<ApiResponse> send(@RequestBody MailRequest request) throws Exception {
		MultipartFile image = getImageContent();
		
		MailBodyContent content = new MailBodyContent();
		
		if(request == null) {
			return new ApiResponse().errorSend(HttpStatus.FAILED_DEPENDENCY, "Failed Service");
		}
		
		content.setUsername(request.getUsername());
		List<String> mailData = new ArrayList<>();
		
		mailData.add(request.getTeamname());
		mailData.add(request.getPurpose());
		mailData.add(request.getCpu());
		mailData.add(request.getStorage());
		mailData.add(request.getOs());
		mailData.add(request.getInstance());
		
		content.setMailData(mailData);

		content.setMessage("Thank you,");
		content.setMailData(mailData);
		
		service.sendEmail(request.getMailTo(), request.getSubject(), image, imageSource, content);
		
		return new ApiResponse(request).send(HttpStatus.OK);
	}
	
	private MultipartFile getImageContent() throws Exception {
		InputStream imageIs = null;
		byte[] imageByteArray = null;
		MultipartFile multipartFile = null;
		imageIs = this.getClass().getClassLoader().getResourceAsStream("templates/" + templateMailBodyImageVal);
		imageByteArray = IOUtils.toByteArray(imageIs);
		multipartFile = new MockMultipartFile(imageIs.getClass().getName(), imageIs.getClass().getName(), "image/jpeg",
				imageByteArray);
		imageSource = new ByteArrayResource(imageByteArray);
		return multipartFile;
	}
}