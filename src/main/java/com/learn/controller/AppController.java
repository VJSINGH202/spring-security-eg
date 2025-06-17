package com.learn.controller;

import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class AppController {

	 @GetMapping("/publicPage")
	 public String pubicPage() {
		 String msg ="Welcome to public page";
		 
	 	return msg;
	 }
	 
	 
	 @GetMapping("/restrictPage")
	 @ResponseBody
	 public String restrictPage(Authentication authentication) {
		 
		 
		 System.out.println(authentication);
		 
	 	return new String();
	 }
	 
	 
	 @GetMapping("/")
	 public String homePage() {
		 String msg ="Welcome to home page";
		 
	 	return msg;
	 }
	 
	 @GetMapping("/private")
	 public String privatePage(Authentication authentication) {
		 String msg =null;	 
		 
		 if(authentication.getPrincipal() instanceof OidcUser oidcUser) {
			 String authorities = oidcUser.getAuthorities().stream().map(authorites -> authorites.getAuthority()).collect(Collectors.joining(","));
			  String name = oidcUser.getName(); 
			  String email = oidcUser.getEmail();
			  
			  msg ="Welcome to private page Name :%s | Email :%s | Authority :%s".formatted(name,email,authorities);
		 }else {
			 msg ="Welcome to private page "+authentication.getName();
		 }
	
	 	return msg;
	 }
	 
}
