package com.documentflowmanagementfordebureaucratization.successfullysigned.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


public class CrmService {
	

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String name;
	
	@Setter
	@Getter
	private String userName;
}
