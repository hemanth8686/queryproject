package com.example.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
	
	/*@Scheduled(cron = "11 40 *	*  * * * ", zone = "Asia/Colombo")
	public void scheduleMailGenerator() throws MessagingException {
		
		
		
		  MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        helper.setSubject("test");
	        helper.setText("test");
	      
	     
	       System.out.println("in testing");
	       
	    // add email addresses 
	   
	        helper.setFrom("vrsroom@gmail.com");
	    //    helper.setTo(InternetAddress.parse("hemanthk887@gmail.com,hemanth@newcastletechnologies.com"));
	      
	        List<String> test=new ArrayList<String>();
	       
	        
	       
	        FileSystemResource file = new FileSystemResource("E:\\New folder\\queries.xlsx");
	        helper.addAttachment(file.getFilename(), file);

	        javaMailSender.send(message);

		
	}*/
	@Scheduled(cron = "0 43 2	*  * * * ", zone = "Indian/Maldives")
	public void scheduleMailGeneratortest() throws MessagingException {
		StringBuilder builderTo=new StringBuilder();
		StringBuilder builderCc=new StringBuilder();
		List<String> emailtestTo = queryService.toEmailtestTo();
		List<String> emailtestCc = queryService.toEmailtestCc();
		for (String string : emailtestTo) {
			builderTo.append(string);
		}
		for (String string : emailtestCc) {
			builderCc.append(string);
		}
		String stringTestTo = builderTo.toString();
		String stringTestCc = builderCc.toString();
		System.out.println(stringTestTo+"stringTest");
		  Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.port", "587");
	      props.put("mail.debug", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props. put("mail.smtp.ssl.trust", "smtp.gmail.com");
	      Session session = Session.getInstance(props,
	  		  new Authenticator() {
	  			protected PasswordAuthentication getPasswordAuthentication() {
	  				return new PasswordAuthentication("vrsroom@gmail.com", "hemanth@09");
	  			}
	  		  });
	    
	       try {
	    	   
		        MimeMessage msg = new MimeMessage(session);
		        msg.setFrom(new InternetAddress("vrsroom@gmail.com"));
		        
	           
	            
		        
		      
		       
			       
		        msg.setRecipients(Message.RecipientType.TO, stringTestTo);
		        msg.setRecipients(Message.RecipientType.CC, stringTestCc);
		        msg.setSubject("Text Mail with Attachment.");
		        msg.setSentDate(new Date());
		        
		        Multipart multipart = new MimeMultipart();
		        
		        MimeBodyPart textPart = new MimeBodyPart();
		        String textContent = "Please find the Attachment.";
		        textPart.setText(textContent);
		        multipart.addBodyPart(textPart);
		        
		        MimeBodyPart attachementPart = new MimeBodyPart();
		        attachementPart.attachFile("E:\\New folder\\queries.xlsx");
		        multipart.addBodyPart(attachementPart);

		        msg.setContent(multipart);
		        Transport.send(msg);
		        System.out.println("---Done---");
	       } catch (Exception ex) {
	    	    ex.printStackTrace();
	       }
	}

		
	

}
