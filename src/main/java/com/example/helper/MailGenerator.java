package com.example.helper;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.model.EmployeeModel;
import com.example.service.QueryService;

@Component
public class MailGenerator {
	
	@Autowired
    private JavaMailSender javaMailSender;

	
	
	@Autowired
	private QueryService queryService;
	
	@Scheduled(cron = "10 12 *	*  * * * ", zone = "Asia/Colombo")
	public void scheduleMailGenerator() throws MessagingException {
		
		
		
		  MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        helper.setSubject("test");
	        helper.setText("test");
	      
	     
	       System.out.println("in testing");
	       
	    // add email addresses 
	   
	        helper.setFrom("vrsroom@gmail.com");
	        helper.setTo("hemanth@newcastletechnologies.com");
	        FileSystemResource file = new FileSystemResource("E:\\New folder\\CountriesDetails.xlsx");
	        helper.addAttachment(file.getFilename(), file);

	        javaMailSender.send(message);

		
	}

}
