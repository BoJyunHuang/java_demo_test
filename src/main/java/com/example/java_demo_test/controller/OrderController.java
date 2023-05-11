package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderRequest;
import com.example.java_demo_test.vo.OrderResponse;

import io.swagger.v3.oas.annotations.Hidden;

//import springfox.documentation.annotations.ApiIgnore;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
//	@ApiIgnore
	@Hidden
	@PostMapping(value = "add_menus")
	public OrderResponse addMenus(@RequestBody OrderRequest request) {
		return orderService.addMenus(request.getMenuList());
	}
	
	@GetMapping(value = "get_all_menus")
	public OrderResponse getAllMenus(){
		return orderService.getAllMenus();
	}
	
	@GetMapping(value = "get_one_menu")
	public OrderResponse getOneMenu(@RequestBody OrderRequest request) {
		return orderService.getOneMenu(request.getName());
	}
	@GetMapping(value = "get_menu_by_name")
	public GetMenuResponse getMenuByName(@RequestBody OrderRequest request) {
		return orderService.getMenuByName(request.getName());
	}
	
	@GetMapping(value = "order")
	public OrderResponse order(@RequestBody OrderRequest request) {
		return orderService.order(request.getOrderMap());
	}
	
	@GetMapping(value = "revise_menu")
	public OrderResponse updateMenuPrice(@RequestBody OrderRequest request) {
		return orderService.updateMenuPrice(request.getMenuList());
	}
}
