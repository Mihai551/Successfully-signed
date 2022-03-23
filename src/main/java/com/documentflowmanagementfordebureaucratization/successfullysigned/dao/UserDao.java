package com.documentflowmanagementfordebureaucratization.successfullysigned.dao;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
