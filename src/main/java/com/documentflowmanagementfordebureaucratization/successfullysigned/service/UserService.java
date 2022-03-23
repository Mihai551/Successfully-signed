package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.User;
import com.documentflowmanagementfordebureaucratization.successfullysigned.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
}
