package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.repository.BankDao;
import com.example.java_demo_test.repository.MenuDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.BankResponse;
import com.example.java_demo_test.vo.OrderResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class OrderTest {

	@Autowired
	private MenuDao menuDao; // �ޥΤ����A�|�۰ʾɦV��@���O

	@Autowired
	private OrderService orderService;

	@Test
	public void addMenuTest() {
		List<Menu> dishes = new ArrayList<>();
		OrderResponse res = orderService.addMenus(dishes);
		List<Menu> resList = res.getMenulist();
		Assert.isTrue(resList != null, "�s�W�\�I���~!");
	}

	@Test
	public void orderTest() {
		Map<String, Integer> order = new HashMap<>();
		order.put("������", 2);
		order.put("�K�N��", 4);
		order.put("����", 10);
		order.put("", 10);
		orderService.order(order);
//		Assert.isTrue(orderService.order(order).getMessage() == "�ƶq���~!", "���ɥ���");
	}

}