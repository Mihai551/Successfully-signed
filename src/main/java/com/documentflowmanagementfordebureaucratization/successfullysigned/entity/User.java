package com.documentflowmanagementfordebureaucratization.successfullysigned.entity;

import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Setter
	@Getter
	private Long id;

	@Column(name = "username")
	@Setter
	@Getter
	private String userName;

	@Column(name = "password")
	@Setter
	@Getter
	private String password;

	@Column(name = "first_name")
	@Setter
	@Getter
	private String firstName;

	@Column(name = "last_name")
	@Setter
	@Getter
	private String lastName;

	@Column(name = "email")
	@Setter
	@Getter
	private String email;
	
	@Column(name = "company")
	@Setter
	@Getter
	private String company;
	
	@Setter
	@Getter
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

	public User() {
	}

	public User(String userName, String password, String firstName, String lastName, String email, String company) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.company = company;
	}

	public User(String userName, String password, String firstName, String lastName, String email, String company,
			Collection<Role> roles) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.company = company;
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", password='" + "*********" + '\''
				+ ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\''
				+ ", roles=" + roles + '}';
	}
}
