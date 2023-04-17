package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.Menu;

public class GetMenuResponse {

	private String message;
	private Menu menu;

	public GetMenuResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetMenuResponse(String message) {
		super();
		this.message = message;
	}

	public GetMenuResponse(Menu menu, String message) {
		super();
		this.menu = menu;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
