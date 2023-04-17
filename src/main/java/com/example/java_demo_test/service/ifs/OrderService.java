package com.example.java_demo_test.service.ifs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

public interface OrderService {

	// 新增餐點
	public OrderResponse addMenus(List<Menu> menuList);

	// 取得所有餐點
	public OrderResponse getAllMenus();

	// 取得單一餐點
	public OrderResponse getOneMenu(String name); 
	public GetMenuResponse getMenuByName(String name);
	
	// 點餐
	public OrderResponse order(Map<String, Integer> orderMap);
}
