package com.example.demosecurity.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSecurityController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
