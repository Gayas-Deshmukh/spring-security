package spring.security.servlet.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController 
{
	/*
	@GetMapping(path = {"/home"})
	public String getHome()
	{
		return "home";
	}
	*/
	
	@GetMapping(path = {"/"})
	public String getHome(Model model, Principal principal, Authentication auth)
	{
		// Principal is sub interface of Authentication interface
		// we can get the GrantedAuthority of the logged in user from Authentication interface
		// we can get the username of the logged in user from Principal interface
		
		String 									name 		= principal.getName();
		Collection<? extends GrantedAuthority> 	authorities = auth.getAuthorities();
		
		model.addAttribute("username", name);
		model.addAttribute("roles", authorities);
		
		return "home";
	}
	
	@GetMapping("/details")
	@ResponseBody
	public String getMyDetails()
	{
		return "My Name is Gayas, I am Java Developer";
	}
	
	@GetMapping("/city")
	@ResponseBody
	public String getMyCity()
	{
		return "I am living in Nashik";
	}
	
	@GetMapping(path = {"/admin-dash"})
	public String adminDashboard(Model model, Principal principal)
	{
		String	name	= principal.getName();		
		model.addAttribute("username", name);
		
		return "admin-dashboard";
	}
	
	@GetMapping(path = {"/user-dash"})
	public String userDashboard(Model model, Principal principal)
	{
		String	name	= principal.getName();		
		model.addAttribute("username", name);
		
		return "user-dashboard";
	}
	
	@GetMapping(path = {"/access-denied"})
	public String accessDenied()
	{
		return "access-denied";
	}
}
