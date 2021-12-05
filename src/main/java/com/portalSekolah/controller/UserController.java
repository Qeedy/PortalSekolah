package com.portalSekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portalSekolah.model.ModelUserRegistration;
import com.portalSekolah.service.UserService;

@RestController
@RequestMapping(path = "user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/register", produces = { "application/json" }, consumes = { "application/json" })
	public String register(@RequestBody ModelUserRegistration modelUserRegistration) {
		return userService.register(modelUserRegistration);
	}

	@GetMapping("/confirm")
	public String confirm(@RequestParam("token") String token) {
		return userService.confirmToken(token);
	}
}
