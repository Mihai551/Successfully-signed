package com.documentflowmanagementfordebureaucratization.successfullysigned.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.documentflowmanagementfordebureaucratization.successfullysigned.validation.FieldMatch;
import com.documentflowmanagementfordebureaucratization.successfullysigned.validation.ValidEmail;

import lombok.Getter;
import lombok.Setter;

@com.documentflowmanagementfordebureaucratization.successfullysigned.validation.FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class CrmUser {
	

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String lastName;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String email;
	
	@Setter
	@Getter
	private String company;


	public CrmUser() {

	}

}
