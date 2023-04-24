package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping(value = "register")
	public LoginResponse register(@RequestBody LoginRequest request) {
		return loginService.register(request);
	}
	
	@PostMapping(value = "activate")
	public LoginResponse activate(@RequestBody LoginRequest request) {
		return loginService.activate(request.getAccount(),request.getPassword());
	}
	
	@GetMapping(value = "sign_in")
	public LoginResponse signIn(@RequestBody LoginRequest request) {
		return loginService.signIn(request.getAccount(),request.getPassword());
	}
	
	@GetMapping(value = "find_users_by_city")
	public LoginResponse findUsersByCity(@RequestBody LoginRequest request) {
		return loginService.findUsersByCity(request.getCity());
	}
	
}
