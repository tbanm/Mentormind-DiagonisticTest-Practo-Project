package com.test.controller;

import java.util.Map;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.entity.User;
import com.test.service.UserService;

import jakarta.servlet.http.HttpServletRequest;




@Controller
public class RegisterController {
	
	
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserService userService;
	
	
	@Autowired
	public RegisterController( UserService userService) {
		
		//this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userService = userService;
	}

	
    @GetMapping("/register")
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@PostMapping("/register")
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Validated User user, BindingResult bindingResult, HttpServletRequest request) {
				
		// Lookup user in database by e-mail
		User userExists = userService.findByEmail(user.getEmail());
		
		System.out.println(userExists);
		
		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("register");		
		} else { // new user so we create user and send confirmation e-mail
					
			// Disable user until they click on confirmation link in email
		   
		    userService.saveUser(user);
				
		//	String appUrl = request.getScheme() + "://" + request.getServerName();
			
		    
			modelAndView.setViewName("register");
		}
			
		return modelAndView;
	}
	
	
	@GetMapping("/confirm")
	public ModelAndView confirmRegistration(ModelAndView modelAndView, @RequestParam("token") String token) {
			
	
			
		modelAndView.setViewName("confirm");
		return modelAndView;		
	}
	
	
	@PostMapping("/confirm")
	public ModelAndView confirmRegistration(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
				
		modelAndView.setViewName("confirm");
		
		

		modelAndView.addObject("successMessage", "Your password has been set!");
		return modelAndView;		
	}
	
	
	
}
