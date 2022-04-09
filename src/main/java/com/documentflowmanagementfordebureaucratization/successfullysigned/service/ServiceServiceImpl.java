package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.documentflowmanagementfordebureaucratization.successfullysigned.dao.ServiceDao;
import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Service;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceDao serviceDao;


	@Override
	@Transactional
	public Service findServiceById(Long id) {
		System.out.print("ServiceService.findbyID:  " + serviceDao.findServicebyId(id).getId());
		return serviceDao.findServicebyId(id);
	}

	@Override
	@Transactional
	public Collection<Service> getServices() {
		// TODO Auto-generated method stub
		return serviceDao.getServices();
	}

}
