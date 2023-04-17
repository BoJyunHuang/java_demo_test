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
		Optional<Menu> item = menuDao.findById(name);
		if (item.isEmpty()) {
			return new OrderResponse("�L���\�I!");
		}
		Menu target = new Menu();
		target.setName(name);
		target.setPrice(item.get().getPrice());
		return new OrderResponse(target, "���o�\�I���\");
	}
	
	@Override
	public GetMenuResponse getMenuByName(String name) {
		Optional<Menu> item = menuDao.findById(name);
		if (item.isEmpty()) {
			return new GetMenuResponse("�L���\�I!");
		}
		Menu target = new Menu();
		target.setName(name);
		target.setPrice(item.get().getPrice());
		return new GetMenuResponse(target, "���o�\�I���\");
	}

	@Override
	public OrderResponse order(Map<String, Integer> orderMap) {
		List<String> orderList = new ArrayList<>(); // �x�sMap�����ƶq���T���~��
		int total = 0;
		Map<String, Integer> firmOrder = new HashMap<>(); // �x�s�̫᥿�T�q��
		// �ˬd��J�\�I���T�P�_
		for (Entry<String, Integer> o : orderMap.entrySet()) {
			if (o.getValue() < 0) { // ���׼ƶq�N�n�A�\�I���~���šA���v�T�p��
				return new OrderResponse("�\�I�ƶq���~!");
			}
			orderList.add(o.getKey()); // �x�s�\�I�W��
		}
		List<Menu> result = menuDao.findAllById(orderList); // �M��}�C�������\�I
		// �p�⥿�T�~���������P�`����
		for (Menu r : result) {
			for (Entry<String, Integer> o : orderMap.entrySet()) {
				if (r.getName().equals(o.getKey())) { // �~���W�٬ۦP��
					int subTotal = r.getPrice() * o.getValue(); // ��@�~���`����
					total += subTotal; // �p���`��
					firmOrder.put(o.getKey(), o.getValue());
				}
			}
		}
		total = total > 500 ? (int) (total * 0.9) : total;
		return new OrderResponse(firmOrder, total, "�I�\���\!");
	}


}
