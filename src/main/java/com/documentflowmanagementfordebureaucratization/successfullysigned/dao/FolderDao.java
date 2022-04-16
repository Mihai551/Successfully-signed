package com.documentflowmanagementfordebureaucratization.successfullysigned.dao;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Folder;

public interface FolderDao {
	
	public Folder findFolderById(Long theId);
	
	void save(Folder theFolder);

}
