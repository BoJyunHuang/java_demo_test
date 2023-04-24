package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Login;
import com.fasterxml.jackson.annotation.JsonInclude;

public class LoginResponse {

	private String message;
	private Login login;
	private List<Login> loginList;
	private LoginRequest request;
	
	public LoginResponse() {
		super();
	}

	public LoginResponse(String message) {
		super();
		this.message = message;
	}

	public LoginResponse(Login login, String message) {
		super();
		this.login = login;
		this.message = message;
	}

	public LoginResponse(List<Login> loginList, String message) {
		super();
		this.loginList = loginList;
		this.message = message;
	}
	
	public LoginResponse(LoginRequest request, String message) {
		super();
		this.request = request;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Login> getLoginList() {
		return loginList;
	}

	public void setLoginList(List<Login> loginList) {
		this.loginList = loginList;
	}

	public LoginRequest getRequest() {
		return request;
	}

	public void setRequest(LoginRequest request) {
		this.request = request;
	}

}
