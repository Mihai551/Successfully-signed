package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.documentflowmanagementfordebureaucratization.successfullysigned.dao.FolderDao;
import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Folder;


@Service
public class FolderServiceImpl implements FolderService {
	
	@Autowired
	private FolderDao folderDao;

	@Override
	@Transactional
	public Folder findFolderById(Long id) {
		
		return folderDao.findFolderById(id);
	}

}
