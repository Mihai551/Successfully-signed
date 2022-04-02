package com.documentflowmanagementfordebureaucratization.successfullysigned.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Collection;
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

	// add request mapping for /leaders

	@GetMapping("/leaders")
	public String showLeaders() {

		return "leaders";
	}

	// add request mapping for /systems

	@GetMapping("/systems")
	public String showSystems() {

		return "systems";
	}

	@GetMapping("/new-service")
	public String defineService(Model theModel) {
		theModel.addAttribute("crmService", new CrmService());
		return "new-service";
	}

	@PostMapping("/new-service-process")
	public String defineServiceProcess(@Valid @ModelAttribute("crmService") CrmService theCrmService,
			BindingResult theBindingResult, Model theModel) {

		User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

		Service service = new Service();
		service.setName(theCrmService.getName());
		service.setUser(user);

		Collection<Service> newService = new ArrayList<Service>();
		newService.add(service);

		userService.saveService(user, newService);

		return "home";
	}

	@GetMapping("/my-services")
	public String getMyServices(HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User updatedUser = userService.findByUserName(auth.getName());
		System.out.print("puuulalalalalala: " + updatedUser.getServices().size());
		session.setAttribute("user", updatedUser);

		return "my-services";
	}

	@PostMapping("/add-step")
	public String addStep(@Valid @ModelAttribute("crmService") CrmService theCrmService, BindingResult theBindingResult,
			Model theModel, HttpSession session) {

		/*
		 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 * User updatedUser = userService.findByUserName(auth.getName());
		 * session.setAttribute("user", updatedUser);
		 */
		
		CrmStep theCrmStep = new CrmStep();

		Service service = serviceService.findServiceById(theCrmService.getId());
		theCrmService.setName(service.getName());
		theModel.addAttribute("crmService", theCrmService);
		theModel.addAttribute("crmStep", new CrmStep());

		return "add-step";
	}

	@PostMapping("/process-add-step")
	public String String (@Valid @ModelAttribute("crmStep") CrmStep theCrmStep, BindingResult theBindingResult,
			Model theModel, HttpSession session) {
		System.out.print("@DEBUG SERVICE ID DIN FRONTEND :  "+theCrmStep.getDocumentName() + "  " + theCrmStep.getAction());
		System.out.print("@DEBUG SERVICE ID DIN FRONTEND :  "+theCrmStep.getServiceId());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(auth.getName());
		
		Service service = serviceService.findServiceById(theCrmStep.getServiceId());
		
		
		Step step = new Step(theCrmStep.getAction(), theCrmStep.getDocumentName());
		step.setService(service);
		Collection<Step> newStep = new ArrayList<Step>();
		newStep.add(step);
		
		service.setSteps(newStep);
		service.setUser(user);
		Collection<Service> newService = new ArrayList<Service>();
		newService.add(service);

		
		//user.setServices(newService);
		
		userService.saveService(user, newService);
		CrmService theCrmService = new CrmService();
		theCrmService.setId(theCrmStep.getServiceId());
		theModel.addAttribute("crmService", theCrmService);
		
		return "forward:/add-step";
		
	}
}
