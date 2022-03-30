package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Service;
import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.User;
import com.documentflowmanagementfordebureaucratization.successfullysigned.model.CrmUser;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
	
	

	void saveService(User user, Collection<Service> service);
}
