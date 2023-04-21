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
		if (CollectionUtils.isEmpty(menuList)) { // ���תŶ��X (menus == null || menus.isEmpty())
			return new OrderResponse("��J���~!");
		}
		for (Menu m : menuList) { // �ˬdlist������Ʀ��L���~
			if (!StringUtils.hasText(m.getName())) { // �ŦX�\�I�W�ٮ榡�Τ��ର�ť�
				return new OrderResponse("�\�I�W�ٿ��~!");
			}
			if (m.getPrice() < 1) { // ���椣�o�p��1
				return new OrderResponse("�\�I������~!");
			}
		}
		List<Menu> res = (List<Menu>) menuDao.saveAll(menuList); // �x�s�Ҧ����(�浧�x�s�|�ܯӶO�귽)
		return new OrderResponse(res, "�s�W���\!");
	}

	@Override
	public OrderResponse getAllMenus() {
		return new OrderResponse(menuDao.findAll(), "���o�\�I���\!");
	}

	@Override
	public OrderResponse getOneMenu(String name) {
		if (!StringUtils.hasText(name)) {
			return new OrderResponse("��J���~!!");
		}
		Optional<Menu> item = menuDao.findById(name);
		if (!item.isPresent()) {
			return new OrderResponse("�L���\�I!");
		}
		return new OrderResponse(item.get(), "���\!");
	}

	@Override
	public GetMenuResponse getMenuByName(String name) {
		if (!StringUtils.hasText(name)) {
			return new GetMenuResponse("��J���~!!");
		}
		Optional<Menu> item = menuDao.findById(name);
		if (!item.isPresent()) {
			return new GetMenuResponse("�L���\�I!");
		}
		return new GetMenuResponse(item.get(), "���\!");
	}

	@Override
	public OrderResponse order(Map<String, Integer> orderMap) {
		if (CollectionUtils.isEmpty(orderMap)) { // ����J���šA�קK�Ŷ]�ΤU���j��X��
			return new OrderResponse("��J����!");
		}
		Map<String, Integer> firmOrder = new HashMap<>(); // �x�s�̫᥿�T�q��
		int total = 0; // �`����
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		// ��k�@
//		List<Menu> allMenuList = menuDao.findAll(); // ���o�Ҧ����
//		for (Entry<String, Integer> o : orderMap.entrySet()) { // ��M�P�T�{�q��
//			if (o.getValue() < 1) { // �ư����~
//				return new OrderResponse("�\�I�ƶq���~!");
//			}
//			for (Menu m : allMenuList) { // ��M�����\�I
//				if (m.getName().equals(o.getKey())) { // �~���ۦP��
//					int subTotal = m.getPrice() * o.getValue(); // ��@�~���`����
//					total += subTotal; // �p���`��
//					firmOrder.put(o.getKey(), o.getValue()); // �x�s�q��
//				}
//			}
//		}
		// ��k�G
		for (Entry<String, Integer> o : orderMap.entrySet()) { // ��M�P�T�{�q��
			if (o.getValue() < 1) {
				return new OrderResponse("�\�I�ƶq���~!");
			}
			Optional<Menu> orderItem = menuDao.findById(o.getKey());
			if (!orderItem.isPresent()) { // �L�~���A�h���ܰj���~�����
				continue;
			}
			int subTotal = orderItem.get().getPrice() * o.getValue(); // ��@�~���`����
			total += subTotal; // �p���`��
			firmOrder.put(o.getKey(), o.getValue()); // �x�s�q��
		}
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (firmOrder.size() == 0) {
			return new OrderResponse("�d�L���!");
		}
		total = total > 500 ? (int) (total * 0.9) : total; // �馩
		return new OrderResponse(firmOrder, total, "�I�\���\!");

	}

	@Override
	public OrderResponse updateMenuPrice(List<Menu> menuList) {
		/*
		 * ��k�@ -- ���`��
		 */
		if (CollectionUtils.isEmpty(menuList)) { // ����J���šA�קK�Ŷ]�ΰj��X��
			return new OrderResponse("��J����!");
		}
		List<Menu> originalMenu = menuDao.findAll();
		List<Menu> reviseMenu = new ArrayList<>();
		for (Menu m : menuList) {
			if (m.getPrice() < 0) { // ���b
				return new OrderResponse("�������~!");
			}
			for (Menu o : originalMenu) { // ��M�����\�I
				if (o.getName().equals(m.getName())) {
					reviseMenu.add(m); // �����ק���
				}
			}
		}
		return new OrderResponse(menuDao.saveAll(reviseMenu), "�ק令�\!");

		/*
		 * ��k�G -- �ϥ�existsById()
		 */
//		if (CollectionUtils.isEmpty(menuList)) { // ����J���šA�קK�Ŷ]�ΰj��X��
//			return new OrderResponse("��J����!");
//		}
//		List<Menu> updateMenus = new ArrayList<>();
//		for (Menu menu:menuList) {
//			if (menu.getPrice()<1) {
//				return new OrderResponse("������~!");
//			}
//			if (menuDao.existsById(menu.getName())) {
//				updateMenus.add(menu);
//			}
//		}
//		if (updateMenus.size() == 0) {
//			return new OrderResponse("�d�L���!");
//		}
//		return new OrderResponse(menuDao.saveAll(updateMenus),"Successful!");

	}

}
