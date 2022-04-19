package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import lombok.Getter;
import lombok.Setter;


public class EmailService extends Thread {

	private JavaMailSender emailSender = Email.getJavaMailSender();
	@Setter
	@Getter
	private String to;
	@Setter
	@Getter
	private String subject;
	@Setter
	@Getter
	private String text;

	public EmailService(String to, String subject, String text) {
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public EmailService() {
		super();
	}

	public void run() {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("Successfully-Signed");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	
}