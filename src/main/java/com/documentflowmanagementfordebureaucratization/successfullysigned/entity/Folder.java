package com.documentflowmanagementfordebureaucratization.successfullysigned.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "folder")
public class Folder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Setter
	@Getter
	private Long id;
	
	@Column(name = "step_no")
	@Setter
	@Getter
	private int step_no;
	
	
	
	
	@Setter
	@Getter
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private User user;
	
	@Setter
	@Getter
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "service_id")
	private Service service;
	
	@Setter
	@Getter
	@OneToMany(mappedBy = "folder", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH, CascadeType.ALL })
	private Collection<Document> documents;
	
	@Column(name = "service_id", insertable = false, updatable = false)
	@Setter
	@Getter
	private long serviceId;

	public Folder() {
		
	}

	public Folder(int step_no, User user, Service service) {
		super();
		this.step_no = step_no;
		this.user = user;
		this.service = service;
	}
	
	
	

}
