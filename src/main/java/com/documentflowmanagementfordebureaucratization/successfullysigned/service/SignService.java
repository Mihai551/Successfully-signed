package com.documentflowmanagementfordebureaucratization.successfullysigned.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.documentflowmanagementfordebureaucratization.successfullysigned.model.SignaturesValidation;

public interface SignService {

	void sign(String userName, long documentId, String password) throws FileNotFoundException, Exception;

	List<SignaturesValidation> signaturesValidation(long documentId);

}
