package com.documentflowmanagementfordebureaucratization.successfullysigned.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "step")
public class Step {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Setter
	@Getter
	private Long id;

	@Column(name = "action")
	@Setter
	@Getter
	private String action;

	@Column(name = "document_name")
	@Setter
	@Getter
	private String documentName;

	@Column(name = "no")
	@Setter
	@Getter
	private int no;

	@Setter
	@Getter
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "service_id")
	private Service service;

	public Step() {

	}

	public Step(String action, String documentName) {
		super();
		this.action = action;
		this.documentName = documentName;
	}

}
