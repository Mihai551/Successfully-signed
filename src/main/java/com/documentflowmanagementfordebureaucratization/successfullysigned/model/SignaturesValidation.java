package com.documentflowmanagementfordebureaucratization.successfullysigned.model;

import lombok.Getter;
import lombok.Setter;

public class SignaturesValidation {
	
	@Setter
	@Getter
	private String signature;
	
	@Setter
	@Getter
	private String signer;
	
	@Setter
	@Getter
	private String validity; 
	
	public SignaturesValidation(String signature, String signer, String validity) {
		super();
		this.signature = signature;
		this.signer = signer;
		this.validity = validity;
	}
	
	public SignaturesValidation() {
		super();
	}

}
