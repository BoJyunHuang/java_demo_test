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

import com.example.java_demo_test.entity.Bank;
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
		if (!StringUtils.hasText(name)) {
			return new OrderResponse("輸入錯誤!!");
		}
		Optional<Menu> item = menuDao.findById(name);
		if (!item.isPresent()) {
			return new OrderResponse("無此餐點!");
		}
		return new OrderResponse(item.get(), "成功!");
	}

	@Override
	public GetMenuResponse getMenuByName(String name) {
		if (!StringUtils.hasText(name)) {
			return new GetMenuResponse("輸入錯誤!!");
		}
		Optional<Menu> item = menuDao.findById(name);
		if (!item.isPresent()) {
			return new GetMenuResponse("無此餐點!");
		}
		return new GetMenuResponse(item.get(), "成功!");
	}

	@Override
	public OrderResponse order(Map<String, Integer> orderMap) {
		if (CollectionUtils.isEmpty(orderMap)) { // 防輸入為空，避免空跑及下面迴圈出錯
			return new OrderResponse("輸入為空!");
		}
		Map<String, Integer> firmOrder = new HashMap<>(); // 儲存最後正確訂單
		int total = 0; // 總價格
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		// 方法一
//		List<Menu> allMenuList = menuDao.findAll(); // 取得所有菜單
//		for (Entry<String, Integer> o : orderMap.entrySet()) { // 找尋與確認訂單
//			if (o.getValue() < 1) { // 排除錯誤
//				return new OrderResponse("餐點數量錯誤!");
//			}
//			for (Menu m : allMenuList) { // 找尋對應餐點
//				if (m.getName().equals(o.getKey())) { // 品項相同時
//					int subTotal = m.getPrice() * o.getValue(); // 單一品項總價格
//					total += subTotal; // 計算總價
//					firmOrder.put(o.getKey(), o.getValue()); // 儲存訂單
//				}
//			}
//		}
		// 方法二
		for (Entry<String, Integer> o : orderMap.entrySet()) { // 找尋與確認訂單
			if (o.getValue() < 1) {
				return new OrderResponse("餐點數量錯誤!");
			}
			Optional<Menu> orderItem = menuDao.findById(o.getKey());
			if (!orderItem.isPresent()) { // 無品項，則跳至迴圈繼續執行
				continue;
			}
			int subTotal = orderItem.get().getPrice() * o.getValue(); // 單一品項總價格
			total += subTotal; // 計算總價
			firmOrder.put(o.getKey(), o.getValue()); // 儲存訂單
		}
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (firmOrder.size() == 0) {
			return new OrderResponse("查無菜單!");
		}
		total = total > 500 ? (int) (total * 0.9) : total; // 折扣
		return new OrderResponse(firmOrder, total, "點餐成功!");

	}

	@Override
	public OrderResponse updateMenuPrice(List<Menu> menuList) {
		/*
		 * 方法一 -- 較常用
		 */
		if (CollectionUtils.isEmpty(menuList)) { // 防輸入為空，避免空跑或迴圈出錯
			return new OrderResponse("輸入為空!");
		}
		List<Menu> originalMenu = menuDao.findAll();
		List<Menu> reviseMenu = new ArrayList<>();
		for (Menu m : menuList) {
			if (m.getPrice() < 0) { // 防呆
				return new OrderResponse("價錢錯誤!");
			}
			for (Menu o : originalMenu) { // 找尋對應餐點
				if (o.getName().equals(m.getName())) {
					reviseMenu.add(m); // 紀錄修改菜單
				}
			}
		}
		return new OrderResponse(menuDao.saveAll(reviseMenu), "修改成功!");

		/*
		 * 方法二 -- 使用existsById()
		 */
//		if (CollectionUtils.isEmpty(menuList)) { // 防輸入為空，避免空跑或迴圈出錯
//			return new OrderResponse("輸入為空!");
//		}
//		List<Menu> updateMenus = new ArrayList<>();
//		for (Menu menu:menuList) {
//			if (menu.getPrice()<1) {
//				return new OrderResponse("價格錯誤!");
//			}
//			if (menuDao.existsById(menu.getName())) {
//				updateMenus.add(menu);
//			}
//		}
//		if (updateMenus.size() == 0) {
//			return new OrderResponse("查無菜單!");
//		}
//		return new OrderResponse(menuDao.saveAll(updateMenus),"Successful!");

	}

}
