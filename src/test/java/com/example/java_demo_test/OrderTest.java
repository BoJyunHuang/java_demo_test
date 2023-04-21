package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.repository.BankDao;
import com.example.java_demo_test.repository.MenuDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.BankResponse;
import com.example.java_demo_test.vo.OrderResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class OrderTest {

	@Autowired
	private MenuDao menuDao; // 引用介面，會自動導向實作類別

	@Autowired
	private OrderService orderService;

	@Test
	public void addMenuTest() {
		List<Menu> dishes = new ArrayList<>();
		OrderResponse res = orderService.addMenus(dishes);
		List<Menu> resList = res.getMenulist();
		Assert.isTrue(resList != null, "新增餐點錯誤!");
	}

	@Test
	public void orderTest() {
		Map<String, Integer> order = new HashMap<>();
		order.put("牛肉麵", 2);
		order.put("焗烤飯", 4);
		order.put("乾麵", 10);
		order.put("", 10);
		orderService.order(order);
//		Assert.isTrue(orderService.order(order).getMessage() == "數量錯誤!", "建檔失敗");
	}

	@Test
	public void updateMenuPriceTest() {
		List<Menu> t = new ArrayList<>();
		Menu m = new Menu("beef", 150);
		t.add(m);
		orderService.updateMenuPrice(t);
	}

	@Test
	public void Test() {
		List<String> existId = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
		List<String> newId = new ArrayList<>(Arrays.asList("C", "D","E"));
		List<String> reslist = new ArrayList<>();
		for (String nId : newId) {
			if (!existId.contains(nId)) {
				reslist.add(nId);
			}
		}
		System.out.println(reslist);
	}

}
