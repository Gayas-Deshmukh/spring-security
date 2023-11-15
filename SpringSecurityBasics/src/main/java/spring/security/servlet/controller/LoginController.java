package spring.security.servlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import spring.security.config.dao.SignUpDAO;
import spring.security.config.dto.SignUpDTO;

@Controller
public class LoginController 
{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SignUpDAO signUpDAO;
	
	@GetMapping(path = {"/custom-login"})
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/sign-up")
	public String signUp(@ModelAttribute("signup") SignUpDTO signUpDTO)
	{
		return "sign-up";
	}
	
	@PostMapping("/process-sign")
	public String processSignUp(SignUpDTO signUpDTO)
	{
		System.out.println(signUpDTO);
		
		// encode user password
		String uncodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
		
		System.out.println(uncodedPassword);
		
		signUpDTO.setPassword(uncodedPassword);
		
		// save user into db
		
		signUpDAO.saveUser(signUpDTO);
		
		return "redirect:/custom-login";
	}
}
