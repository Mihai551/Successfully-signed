package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.documentflowmanagementfordebureaucratization.successfullysigned.dao.DocumentDao;
import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Document;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao documentDao;
	
	@Override
	public void save(Document document) {
		documentDao.save(document);

	}

	@Override
	public Document findByFolderIdAndName(long folderId, String name) {
		// TODO Auto-generated method stub
		return documentDao.findByFolderIdAndName(folderId, name);
	}
	
	@Override
	public Document findById(long folderId) {
		// TODO Auto-generated method stub
		return documentDao.findById(folderId);
	}

}
