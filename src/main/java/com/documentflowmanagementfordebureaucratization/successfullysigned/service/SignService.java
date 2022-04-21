package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import java.io.FileNotFoundException;

public interface SignService {

	void sign(String userName, long documentId, String password) throws FileNotFoundException, Exception;

}
