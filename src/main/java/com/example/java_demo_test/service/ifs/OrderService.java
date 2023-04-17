package com.example.java_demo_test.service.ifs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

public interface OrderService {

	// �s�W�\�I
	public OrderResponse addMenus(List<Menu> menuList);

	// ���o�Ҧ��\�I
	public OrderResponse getAllMenus();

	// ���o��@�\�I
	public OrderResponse getOneMenu(String name); 
	public GetMenuResponse getMenuByName(String name);
	
	// �I�\
	public OrderResponse order(Map<String, Integer> orderMap);
}
