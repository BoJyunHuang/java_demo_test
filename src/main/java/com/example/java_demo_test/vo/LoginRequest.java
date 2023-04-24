package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.Login;

public class LoginRequest {

	private Login login;
	private String city;

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
