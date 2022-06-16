package com.documentflowmanagementfordebureaucratization.successfullysigned.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Document;
import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Folder;
import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Role;
import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.Service;
import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.User;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.DocumentService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.EmailService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.FolderService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.UserService;

@Controller
public class UploadController {

	@Autowired
	FolderService folderService;

	@Autowired
	DocumentService documentService;

	@Autowired
	UserService userService;

	private final String UPLOAD_DIR = "C:/Users/Mihai/Desktop/Successfully-Signed-Documents/";

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("folderId") long folderId,
			@RequestParam("documentName") String documentName, RedirectAttributes redirectAttributes) {

		// check if file is empty
		if (file.isEmpty()) {
			redirectAttributes.addAttribute("id", folderId);
			return "redirect:/my-folder";
		}

		if (documentName.isBlank()) {
			redirectAttributes.addAttribute("id", folderId);
			return "redirect:/my-folder";
		}

		// save the file on the local file system
		try {

			if (documentService.findByFolderIdAndName(folderId, documentName) == null) {

				Document theDocument = new Document();
				theDocument.setName(documentName);
				theDocument.setFolder(folderService.findFolderById(folderId));
				documentService.save(theDocument);
				theDocument = documentService.findByFolderIdAndName(folderId, documentName);
				Path path = Paths.get(UPLOAD_DIR + theDocument.getId() + ".pdf");
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				// Email sender
				try {
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					User user = userService.findByUserName(auth.getName());

					List<Role> roles = (List<Role>) user.getRoles();

					Folder theFolder = folderService.findFolderById(folderId);
					Service theService = theFolder.getService();

					EmailService theEmailService = new EmailService();

					if (roles.get(0).getName().equals("ROLE_NATURAL_PERSON")) {

						theEmailService.setTo(theService.getUser().getEmail());
						theEmailService.setSubject("File uploaded");
						theEmailService.setText(theFolder.getUser().getUserName() + " has uploaded " + documentName
								+ " in " + theService.getName() + ".");
						theEmailService.start();
					}

					else {
						theEmailService.setTo(theFolder.getUser().getEmail());
						theEmailService.setSubject("File uploaded");
						theEmailService.setText(theService.getUser().getUserName() + " has uploaded " + documentName
								+ " in " + theService.getName() + ".");
						theEmailService.start();
					}

				} catch (Exception e) {
					System.out.print(e);
				}

			}
		} catch (

		IOException e) {
			e.printStackTrace();
		}

		// return success response

		redirectAttributes.addAttribute("id", folderId);

		return "redirect:/my-folder";
	}

}