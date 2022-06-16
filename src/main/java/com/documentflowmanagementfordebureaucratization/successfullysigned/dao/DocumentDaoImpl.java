package com.documentflowmanagementfordebureaucratization.successfullysigned.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Document;

@Repository
public class DocumentDaoImpl implements DocumentDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Document findByFolderIdAndName(long folderId, String name) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Document> theQuery = currentSession
				.createQuery("from Document where name=:docName and folder_id=:folderId", Document.class);
		theQuery.setParameter("docName", name);
		theQuery.setParameter("folderId", folderId);
		Document theDocument = null;
		try {
			theDocument = theQuery.getSingleResult();
		} catch (Exception e) {
			theDocument = null;
		}

		return theDocument;
	}

	@Override
	public void save(Document theDocument) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		
		currentSession.saveOrUpdate(theDocument);
	}

	@Override
	public Document findById(Long theId) {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Document> theQuery = currentSession.createQuery("from Document where id=:theId", Document.class);
		theQuery.setParameter("theId", theId);
		Document theDocument = null;
		try {
			theDocument = theQuery.getSingleResult();
		} catch (Exception e) {
			theDocument = null;
		}

		return theDocument;

	}

}
