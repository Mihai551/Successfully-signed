package com.documentflowmanagementfordebureaucratization.successfullysigned.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Setter
	@Getter
	private Long id;

	@Column(name = "name")
	@Setter
	@Getter
	private String name;

	@Column(name = "company")
	@Setter
	@Getter
	private String company;

	@Setter
	@Getter
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private User user;

	public Service(String name, String company, User user) {
		super();
		this.name = name;
		this.company = company;
		this.user = user;
	}

}
