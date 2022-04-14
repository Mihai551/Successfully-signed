package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Document;

public interface DocumentService {
	
	void save(Document document);

	Document findByFolderIdAndName(long folderId, String name);

}
