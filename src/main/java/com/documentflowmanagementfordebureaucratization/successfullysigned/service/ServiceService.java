package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import java.util.Collection;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Service;

public interface ServiceService {

	public Service findServiceById(Long id);
	
	public Collection<Service> getServices();
}
