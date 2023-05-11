package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;

public class OrderResponse {

	private Menu menu;
	private List<Menu> menuList;
	private Map<String, Integer> orderMap;
	private int totalPrice;
	private String message;

	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderResponse(String message) {
		super();
		this.message = message;
	}

	public OrderResponse(Menu menu, String message) {
		super();
		this.menu = menu;
		this.message = message;
	}

	public OrderResponse(List<Menu> menuList, String message) {
		super();
		this.menuList = menuList;
		this.message = message;
	}
	
	public OrderResponse(Map<String, Integer> orderMap, String message) {
		super();
		this.orderMap = orderMap;
		this.message = message;
	}

	public OrderResponse(Map<String, Integer> orderMap, int totalPrice, String message) {
		super();
		this.orderMap = orderMap;
		this.totalPrice = totalPrice;
		this.message = message;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenulist() {
		return menuList;
	}

	public void setMenulist(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public Map<String, Integer> getOrders() {
		return orderMap;
	}

	public void setOrders(Map<String, Integer> orders) {
		this.orderMap = orders;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
