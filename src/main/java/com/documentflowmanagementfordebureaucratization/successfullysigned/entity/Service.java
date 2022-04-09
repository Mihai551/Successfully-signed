package com.documentflowmanagementfordebureaucratization.successfullysigned.entity;

import java.util.Collection;

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

	@Setter
	@Getter
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private User user;

	@Setter
	@Getter
	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY,  cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH, CascadeType.ALL })
	private Collection<Step> steps;
	
	@Setter
	@Getter
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	private Collection<Folder> folders;

	public Service() {
	}

	public Service(String name, User user) {

		this.name = name;
		this.user = user;

	}

	public Service(String name, User user, Collection<Step> steps) {

		this.name = name;
		this.user = user;
		this.steps = steps;
	}

}
