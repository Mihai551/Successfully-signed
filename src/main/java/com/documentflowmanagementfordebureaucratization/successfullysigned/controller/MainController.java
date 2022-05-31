package com.documentflowmanagementfordebureaucratization.successfullysigned.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.*;
import com.documentflowmanagementfordebureaucratization.successfullysigned.model.CrmService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.model.CrmSign;
import com.documentflowmanagementfordebureaucratization.successfullysigned.model.CrmStep;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.DocumentService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.EmailService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.FolderService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.ServiceService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.SignService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.lang.Object;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private ServiceService serviceService;

	@Autowired
	private FolderService folderService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private SignService signService;

	@GetMapping("/")
	public String showHome() {

		return "home";
	}

	@GetMapping("/new-service")
	public String defineService(Model theModel) {
		theModel.addAttribute("crmService", new CrmService());
		return "new-service";
	}

	@PostMapping("/new-service-process")
	public ModelAndView defineServiceProcess(@Valid @ModelAttribute("crmService") CrmService theCrmService,
			BindingResult theBindingResult, Model theModel) {

		User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

		Service service = new Service();
		service.setName(theCrmService.getName());
		service.setUser(user);

		Collection<Service> newService = new ArrayList<Service>();
		newService.add(service);

		userService.saveService(user, newService);

		return new ModelAndView("redirect:/my-services");

	}

	@GetMapping("/my-services")
	public String getMyServices(HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User updatedUser = userService.findByUserName(auth.getName());
		session.setAttribute("user", updatedUser);

		return "my-services";
	}

	@PostMapping("/add-step")
	public String addStep(@ModelAttribute("crmService") CrmService theCrmService,
			@ModelAttribute("serviceId") String theServiceId, BindingResult theBindingResult, Model theModel,
			HttpSession session) {

		/*
		 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 * User updatedUser = userService.findByUserName(auth.getName());
		 * session.setAttribute("user", updatedUser);
		 */

		if (theServiceId == null || theServiceId.isEmpty()) {
		} else {
			theCrmService.setId(Long.parseLong(theServiceId));
		}

		Service service = serviceService.findServiceById(theCrmService.getId());
		theCrmService.setName(service.getName());
		theModel.addAttribute("theService", service);
		theModel.addAttribute("crmService", theCrmService);
		theModel.addAttribute("crmStep", new CrmStep());
		theModel.addAttribute("theSteps", service.getSteps());

		return "add-step";
	}

	@PostMapping("/process-add-step")
	public ModelAndView processAddStep(@Valid @ModelAttribute("crmStep") CrmStep theCrmStep,
			BindingResult theBindingResult, Model theModel, HttpSession session, HttpServletRequest request,
			ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(auth.getName());

		Service service = serviceService.findServiceById(theCrmStep.getServiceId());

		Step step = new Step(theCrmStep.getAction(), theCrmStep.getDocumentName());
		step.setNo(service.getSteps().size() + 1);
		step.setService(service);
		Collection<Step> newStep = new ArrayList<Step>();
		newStep.add(step);

		service.setSteps(newStep);
		service.setUser(user);
		Collection<Service> newService = new ArrayList<Service>();
		newService.add(service);

		userService.saveService(user, newService);

		User updatedUser = userService.findByUserName(auth.getName());
		session.setAttribute("user", updatedUser);

		CrmService theCrmService = new CrmService();
		theCrmService.setId(theCrmStep.getServiceId());

		model.addAttribute("serviceId", theCrmService.getId().toString());

		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

		return new ModelAndView("redirect:/add-step", model);

	}

	@GetMapping("/new-folder")
	public String newFolder(Model theModel) {
		List<Service> services = (List<Service>) serviceService.getServices();
		theModel.addAttribute("services", services);
		return "new-folder";

	}

	@RequestMapping(value = "process-new-folder", method = RequestMethod.GET)
	public String processNewFolder(@RequestParam("id") Long serviceId) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(auth.getName());

		Service theService = serviceService.findServiceById(serviceId);

		Folder folder = new Folder(1, user, theService);

		user.setFolders(new ArrayList<Folder>(Arrays.asList(folder)));
		userService.saveUser(user);

		// Email sender

		try {
			EmailService emailService = new EmailService();
			emailService.setTo(theService.getUser().getEmail());
			emailService.setSubject("New folder");
			emailService.setText(folder.getUser().getUserName() + " has instantiated " + theService.getName() + ".");
			emailService.start();
		} catch (Exception e) {
			System.out.print(e);
		}

		return "home";

	}

	@GetMapping("/my-folders")
	public String myFolders(Model theModel) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(auth.getName());

		List<Folder> folders = (List<Folder>) user.getFolders();
		for (int i = 0; i < folders.size(); i++) {
			Folder f = folders.get(i);
			f.setService(serviceService.findServiceById(f.getServiceId()));
			folders.set(i, f);
			System.out.println();
		}
		theModel.addAttribute("folders", folders);

		return "my-folders";

	}

	@RequestMapping(value = "my-folder", method = RequestMethod.GET)
	public String myFolder(@RequestParam("id") Long folderId, Model theModel) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(auth.getName());

		List<Role> roles = (List<Role>) user.getRoles();

		if (roles.get(0).getName().equals("ROLE_NATURAL_PERSON")) {

			Folder theFolder = folderService.findFolderById(folderId);
			List<Step> thelistOfSteps = (List<Step>) theFolder.getService().getSteps();

			Step theStep = new Step();

			boolean stepOverFlowFlag = true;
			for (int i = 0; i < thelistOfSteps.size(); i++) {
				theStep = (Step) thelistOfSteps.get(i);
				if (theStep.getNo() == theFolder.getStep_no()) {
					stepOverFlowFlag = false;
					break;
				}
			}

			theModel.addAttribute("step", theStep);
			theModel.addAttribute("folder", theFolder);

			if (stepOverFlowFlag == true) {
				return "finished";
			}

			if (theStep.getAction().equalsIgnoreCase("upload"))
				return "upload";
			else {
				System.out.println(theFolder.getId() + " " + theStep.getDocumentName());
				Document theDocument = documentService.findByFolderIdAndName(theFolder.getId(),
						theStep.getDocumentName());

				System.out.println(theFolder.getId() + " " + theStep.getDocumentName() + " " + theDocument.getId());

				theModel.addAttribute("crmSign", new CrmSign());
				theModel.addAttribute("document", theDocument);
				return "sign";
			}
		} else {

			Folder theFolder = folderService.findFolderById(folderId);

			theModel.addAttribute("folder", theFolder);
			theModel.addAttribute("crmSign", new CrmSign());
			return "folder-administration";
		}

	}

	@RequestMapping(path = "/download", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> download(@RequestParam("id") long id) throws IOException {

		Path path = Paths.get("C:/Users/Mihai/Desktop/Successfully-Signed-Documents/" + id + ".pdf");
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		File file = new File("C:/Users/Mihai/Desktop/Successfully-Signed-Documents/" + id + ".pdf");

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=" + documentService.findById(id).getName() + ".pdf");
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		return ResponseEntity.ok().headers(header).contentLength(file.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@PostMapping(path = "/validate-step")
	public String validateStep(@RequestParam("folderId") String id) {

		long folderId = Long.parseLong(id);

		Folder theFolder = folderService.findFolderById(folderId);
		theFolder.setStep_no(theFolder.getStep_no() + 1);
		folderService.save(theFolder);

		try {
			EmailService emailService = new EmailService();
			emailService.setTo(theFolder.getUser().getEmail());
			emailService.setSubject("Step Validation");
			emailService.setText(
					theFolder.getService().getUser().getUserName() + " has validated your last step. The actual step: <"
							+ theFolder.getStep_no() + ">(" + theFolder.getService().getName() + ").");
			emailService.start();
		} catch (Exception e) {
			System.out.print(e);
		}

		return "home";
	}

	@PostMapping(path = "/sign")
	public String sign(@ModelAttribute("crmSign") CrmSign crmSign) throws FileNotFoundException, Exception {

		System.out
				.print("C:\\Users\\Mihai\\Desktop\\Successfully-Signed-Documents\\" + crmSign.getDocumentId() + ".pdf");
		System.out
				.print("sign API:  " + "userName" + " " + crmSign.getDocumentId() + " " + crmSign.getPassword() + " ");

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.print("sign API:  " + userName + " " + crmSign.getDocumentId() + " " + crmSign.getPassword() + " ");

		if (crmSign.getDocumentId() != 0) {
			signService.sign(userName, crmSign.getDocumentId(), crmSign.getPassword());
		}

		return "home";
	}

}