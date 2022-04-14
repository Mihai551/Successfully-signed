package com.documentflowmanagementfordebureaucratization.successfullysigned.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Folder;

@Repository
public class FolderDaoImpl implements FolderDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Folder findFolderById(Long theId) {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Folder> theQuery = currentSession.createQuery("from Folder where id=:theId", Folder.class);
		theQuery.setParameter("theId", theId);
		Folder theFolder = null;
		try {
			theFolder = theQuery.getSingleResult();
		} catch (Exception e) {
			theFolder = null;
		}

		return theFolder;

	}
}
