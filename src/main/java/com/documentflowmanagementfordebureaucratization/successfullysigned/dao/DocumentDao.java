package com.documentflowmanagementfordebureaucratization.successfullysigned.dao;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Document;

public interface DocumentDao {

	void save(Document document);

	Document findByFolderIdAndName(long folderId, String name);

	Document findById(Long theId);

}
