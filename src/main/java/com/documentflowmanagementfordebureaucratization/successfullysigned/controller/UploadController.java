package com.documentflowmanagementfordebureaucratization.successfullysigned.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Document;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.DocumentService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.FolderService;

@Controller
public class UploadController {

	@Autowired
	FolderService folderService;

	@Autowired
	DocumentService documentService;

	private final String UPLOAD_DIR = "C:/Users/Mihai/Desktop/Successfully-Signed-Documents/";

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("folderId") long folderId,
			@RequestParam("documentName") String documentName, RedirectAttributes attributes) {

		// check if file is empty
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload.");
			return "redirect:/my-folder";
		}

		// normalize the file path
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// save the file on the local file system
		try {
			Document theDocument = new Document();
			theDocument.setName(documentName);
			theDocument.setFolder(folderService.findFolderById(folderId));
			documentService.save(theDocument);
			theDocument = documentService.findByFolderIdAndName(folderId, documentName);
			Path path = Paths.get(UPLOAD_DIR + theDocument.getId() + ".pdf");
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return success response
		attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

		return "redirect:/";
	}

}