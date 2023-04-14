package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.vo.BankResponse;

public interface BankService {

	// 新增資料
	public void addInfo(Bank bank); // 實作方法才需要大括號

	// 取出餘額
	public Bank getAmountById(String id);

	// 存款
	public BankResponse deposit(Bank bank);
	
	// 提款
	public BankResponse withdraw(Bank bank);
	
}
