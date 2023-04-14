package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.repository.BankDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class BankTest {

	@Autowired
	private BankDao bankDao;

	@Autowired
	private BankService bankService;

	@Test
	public void addBankInfo() {
		Bank bank = new Bank("A01", "00000000", 1000); // 新增資料到資料庫'bank'
		bankDao.save(bank);
	}

	@Test
	public void addInfoTest() {
		Bank bank = new Bank("A02", "00000000", 1000);
		bankService.addInfo(bank); // 資料有經過判斷是否符合規定
	}

	@Test
	public void getAmountByIdTest() {
		Bank bank = bankService.getAmountById("A01");
		System.out.println("帳號:" + bank.getAccount() + "，餘額:" + bank.getAmount());
	}

	@Test
	public void depositTest() {
		Bank oldBank = bankDao.save(new Bank("AA999", "AA123456@", 2000)); // 測試用
		Bank newBank = new Bank("AA999", "AA123456@", 3000);
		BankResponse response = bankService.deposit(newBank); // 存款
		// 斷言，確認存款成功與否
		Assert.isTrue(response.getBank().getAmount() == (oldBank.getAmount() + newBank.getAmount()), "存款失敗!"); 
		Assert.isTrue(response.getMessage().equals("存款成功!"), "存款失敗!");
		bankDao.delete(response.getBank()); // 刪除測試數據
	}
	
	@Test
	public void withdrawTest() {
		Bank oldBank = bankDao.save(new Bank("AA999", "AA123456@", 5000)); // 測試用
		Bank newBank = new Bank("AA999", "AA123456@", 2000); // 存款金額
		BankResponse response = bankService.withdraw(newBank); // 存款
		// 斷言，確認存款成功與否
		Assert.isTrue(response.getBank().getAmount() == (oldBank.getAmount() - newBank.getAmount()), "提款失敗!"); 
		Assert.isTrue(response.getMessage().equals("提款成功!"), "提款失敗!");
		bankDao.delete(response.getBank()); // 刪除測試數據
	}

}
