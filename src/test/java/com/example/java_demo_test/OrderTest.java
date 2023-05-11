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

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.OrderResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class OrderTest {

	@Autowired
	private OrderService orderService;

	@Test
	public void addMenuTest() {
		List<Menu> dishes = new ArrayList<>();
		OrderResponse res = orderService.addMenus(dishes);
		List<Menu> resList = res.getMenulist();
		Assert.isTrue(resList != null, "·s¼WÀ\ÂI¿ù»~!");
	}

	@Test
	public void orderTest() {
		Map<String, Integer> order = new HashMap<>();
		order.put("¤û¦×ÄÑ", 2);
		order.put("ÖK¯N¶º", 4);
		order.put("°®ÄÑ", 10);
		order.put("", 10);
		orderService.order(order);
//		Assert.isTrue(orderService.order(order).getMessage() == "¼Æ¶q¿ù»~!", "«ØÀÉ¥¢±Ñ");
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
