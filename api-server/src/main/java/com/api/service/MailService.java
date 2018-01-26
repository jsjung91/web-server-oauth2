package com.api.service;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.api.model.MailBodyContent;

@Component
public class MailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public String sendEmail(String to, String subject, MultipartFile image, InputStreamSource imageSource, MailBodyContent content) throws Exception {
		
		String templateName = "mail/mailForm";
		Context context = new Context();
		
		context.setVariable("Content", content);
		context.setVariable("companyLogo", image.getName());
		
		String body = templateEngine.process(templateName, context);
		
		MimeMessage mail = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		
//		helper.addAttachment("pulpitrock.jpg", new ClassPathResource("templates/.jpg"));
		
		helper.setFrom("sylee@mtos.co.kr");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		
		helper.addInline(image.getName(), imageSource, image.getContentType());
		
//		helper.setCc(cc); 참조자 설정 메서드
		
		sender.send(mail);
		
		return "Mail send successfully !!!"; //JSON 형식으로 변경 예정
	}

}
