package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.repository.MenuDao;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public OrderResponse addMenus(List<Menu> menuList) {
		if (CollectionUtils.isEmpty(menuList)) { // 阻擋空集合 (menus == null || menus.isEmpty())
			return new OrderResponse("輸入錯誤!");
		}
		for (Menu m : menuList) { // 檢查list內部資料有無錯誤
			if (!StringUtils.hasText(m.getName())) { // 符合餐點名稱格式或不能為空白
				return new OrderResponse("餐點名稱錯誤!");
			}
			if (m.getPrice() < 1) { // 價格不得小於1
				return new OrderResponse("餐點價格錯誤!");
			}
		}
		List<Menu> res = (List<Menu>) menuDao.saveAll(menuList); // 儲存所有菜單(單筆儲存會很耗費資源)
		return new OrderResponse(res, "新增成功!");
	}

	@Override
	public OrderResponse getAllMenus() {
		return new OrderResponse(menuDao.findAll(), "取得餐點成功!");
	}

	@Override
	public OrderResponse getOneMenu(String name) {
		Optional<Menu> item = menuDao.findById(name);
		if (item.isEmpty()) {
			return new OrderResponse("無此餐點!");
		}
		Menu target = new Menu();
		target.setName(name);
		target.setPrice(item.get().getPrice());
		return new OrderResponse(target, "取得餐點成功");
	}
	
	@Override
	public GetMenuResponse getMenuByName(String name) {
		Optional<Menu> item = menuDao.findById(name);
		if (item.isEmpty()) {
			return new GetMenuResponse("無此餐點!");
		}
		Menu target = new Menu();
		target.setName(name);
		target.setPrice(item.get().getPrice());
		return new GetMenuResponse(target, "取得餐點成功");
	}

	@Override
	public OrderResponse order(Map<String, Integer> orderMap) {
		List<String> orderList = new ArrayList<>(); // 儲存Map中的數量正確的品項
		int total = 0;
		Map<String, Integer> firmOrder = new HashMap<>(); // 儲存最後正確訂單
		// 檢查輸入餐點正確與否
		for (Entry<String, Integer> o : orderMap.entrySet()) {
			if (o.getValue() < 0) { // 阻擋數量就好，餐點錯誤為空，不影響計價
				return new OrderResponse("餐點數量錯誤!");
			}
			orderList.add(o.getKey()); // 儲存餐點名稱
		}
		List<Menu> result = menuDao.findAllById(orderList); // 尋找陣列中對應餐點
		// 計算正確品項的價錢與總價錢
		for (Menu r : result) {
			for (Entry<String, Integer> o : orderMap.entrySet()) {
				if (r.getName().equals(o.getKey())) { // 品項名稱相同時
					int subTotal = r.getPrice() * o.getValue(); // 單一品項總價格
					total += subTotal; // 計算總價
					firmOrder.put(o.getKey(), o.getValue());
				}
			}
		}
		total = total > 500 ? (int) (total * 0.9) : total;
		return new OrderResponse(firmOrder, total, "點餐成功!");
	}


}
