package com.documentflowmanagementfordebureaucratization.successfullysigned.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.documentflowmanagementfordebureaucratization.successfullysigned.model.CrmUser;
import com.documentflowmanagementfordebureaucratization.successfullysigned.service.UserService;
import java.util.*;

@Controller
public class DemoController {

	@Autowired
	private UserService userService;

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
		
		System.out.println("DEBUG@  "  + theCrmService.getUserName() + "   DEBUG@  " + theCrmService.getName());
		
		User user = userService.findByUserName(theCrmService.getUserName());
		
		Service service =  new Service();
		service.setName(theCrmService.getName());
		service.setUser(user);
		
		Collection<Service> newService = new ArrayList<Service>();
		newService.add(service);
		
		userService.saveService(user, newService);

		return "home";
	}
}
