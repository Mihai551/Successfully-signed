package com.documentflowmanagementfordebureaucratization.successfullysigned.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Service;
import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.User;

@Repository
public class ServiceDaoImpl implements ServiceDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Service findServicebyId(Long theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<Service> theQuery = currentSession.createQuery("from Service where id=:theId", Service.class);
		theQuery.setParameter("theId", theId);
		Service theService = null;
		try {
			theService = theQuery.getSingleResult();
		} catch (Exception e) {
			theService = null;
		}

		return theService;
	}
}
