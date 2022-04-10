package com.documentflowmanagementfordebureaucratization.successfullysigned.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.logging.Logger;

import com.documentflowmanagementfordebureaucratization.successfullysigned.entity.*;
import com.documentflowmanagementfordebureaucratization.successfullysigned.model.CrmService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.model.CrmStep;
import com.documentflowmanagementfordebureaucratization.successfullysigned.model.CrmUser;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.ServiceService;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.ServiceServiceImpl;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.UserService;
import java.util.*;

@Controller
public class DemoController {

	@Autowired
	private UserService userService;
	@Autowired
	private ServiceService serviceService;

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

		// user.setServices(newService);

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

		System.out.print("service id pentru folder:  " + serviceId + "  a mers dq  ");

		Folder folder = new Folder(1, user, serviceService.findServiceById(serviceId));

		user.setFolders(new ArrayList<Folder>(Arrays.asList(folder)));
		userService.saveUser(user);

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
		}
		theModel.addAttribute("folders", folders);
		
		return "my-folders";
	}

}