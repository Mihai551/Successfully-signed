package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
@Component
public class Email {

	public static JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.office365.com"); //smtp.gmail.com
		mailSender.setPort(587); //587

		mailSender.setUsername("Successfully.Signed.licenta@gmail.com"); //Successfully.Signed.licenta@gmail.com
		mailSender.setPassword("licentaETTI2022"); //licentaETTI2022

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

}