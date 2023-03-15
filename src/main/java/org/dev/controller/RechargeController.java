package org.dev.controller;

import javax.servlet.http.HttpServletRequest;

import org.dev.dto.UserDTO;
import org.dev.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequestMapping("/")
public class RechargeController {

	@Autowired
	RegistrationService registrationService;

	@GetMapping("/rechargeMobile.krish")
	public String rechargeMobile() {
		System.out.println("Mobile has been recharged");
		return "success.jsp";
	}

	@GetMapping("/rechargeDTH.krish")
	public String rechargeDTH() {
		System.out.println("DTH has been recharged");
		return "success.jsp";
	}

	@GetMapping("/regGM.krish")
	public String registerUser(HttpServletRequest request) {
		String username = request.getParameter("username");
		Long mobileNumber = Long.parseLong(request.getParameter("mobileNumber"));

		System.out.println("Username: " + username);
		System.out.println("Mobile Number: " + mobileNumber);

		return "success.jsp";
	}

	@PostMapping("/regPM.krish")
	public ModelAndView registerUser(@RequestParam String username, @RequestParam String password,
			@RequestParam Long mobileNumber) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		userDTO.setMobileNumber(mobileNumber);

		boolean operationStatus = registrationService.register(userDTO);

		if (operationStatus) {
			return new ModelAndView("success.jsp");
		} else {
			return new ModelAndView("failed.jsp");
		}
	}

	@PostMapping("/regMA.krish")
	public ModelAndView registerUser(@ModelAttribute UserDTO userDTO) {
		boolean operationStatus = registrationService.register(userDTO);

		String status = null;

		if (operationStatus) {
			status = "Save operation was successfull for operation: " + userDTO.getUsername();
		} else {
			status = "Save operation was not successfull. Please try again";
		}
		return new ModelAndView("success.jsp", "operationStatus", status);
	}
}
