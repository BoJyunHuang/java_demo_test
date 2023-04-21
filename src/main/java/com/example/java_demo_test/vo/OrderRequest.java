package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequest {

	private String name;
	private Menu menu;
	@JsonProperty("menu_list")
	private List<Menu> menuList;
	@JsonProperty("order_map")
	private Map<String, Integer> orderMap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public Map<String, Integer> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<String, Integer> orderMap) {
		this.orderMap = orderMap;
	}
}
