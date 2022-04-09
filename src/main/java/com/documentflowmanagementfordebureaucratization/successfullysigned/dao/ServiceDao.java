package com.documentflowmanagementfordebureaucratization.successfullysigned.dao;

import java.util.Collection;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Service;

public interface ServiceDao {

	public Service findServicebyId(Long id);
	
	public Collection<Service> getServices();

}
