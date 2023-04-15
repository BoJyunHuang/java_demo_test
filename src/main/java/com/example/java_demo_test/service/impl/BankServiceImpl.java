package com.example.java_demo_test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.repository.BankDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@Service
public class BankServiceImpl implements BankService {

	private String accountPattern = "\\w{3,8}"; // 帳號正規表達式
	private String pwdPattern = "\\S{8,16}"; // 密碼正規表達式

	@Autowired
	private BankDao bankDao;

	// 檢查欄位資訊正確與否的方法
	private boolean checkInfo(String info, String item, String pattern) {
		if (item == null) { // 該欄位不得為空
			System.out.println(info + "為空!");
			return false;
		} else if (!item.matches(pattern)) { // 欄位資訊不符合型式
			System.out.println(info + "型式不符合規定!");
			return false;
		}
		return true; // 符合型式回傳true
	}

	// 比對帳號密碼正確與否的方法
	private BankResponse compareInfo(Bank bank) {
		if (bank == null || !StringUtils.hasText(bank.getAccount()) || !StringUtils.hasText(bank.getPassword())
				|| bank.getAmount() <= 0) { // 檢查輸入物件是否為空
			return new BankResponse(new Bank(), "帳號/密碼錯誤!");
		}
		Bank resBank = bankDao.findByAccountAndPassword(bank.getAccount(), bank.getPassword()); // 尋找物件是否存在倉庫
		if (resBank == null) { // 若尋找不到，回傳new Bank()
			return new BankResponse(new Bank(), "資料不存在!");
		}
		return new BankResponse(resBank, "OK"); // 回傳正確帳號
	}

	@Override
	public void addInfo(Bank bank) {
		// 資料防呆
		if (bank == null) {
			System.out.println("資料為空!");
			return; // 跳出方法
		}
		// 檢查帳號長度是否符合規定：(1)長度3~8 (2)帳號不能有空白 (3)帳號不能有特殊符號如~!@#$%^&*
		if (!checkInfo("帳號", bank.getAccount(), accountPattern)) {
			return; // 跳出方法
		}
		// 檢查密碼不能有空白或全為空白，且長度為8~16
		if (!checkInfo("密碼", bank.getPassword(), pwdPattern)) {
			return; // 跳出方法
		}
		// 檢查帳戶餘額不得為負
		if (bank.getAmount() < 0) { // 餘額小於0
			System.out.println("餘額有異!");
			return; // 跳出方法
		}
		// 新增資料到資料庫'bank'
		bankDao.save(bank);

	}

	@Override
	public Bank getAmountById(String id) {
		if (!StringUtils.hasText(id)) { // 檢查輸入id是否null或空字串
			return new Bank();
		}
		Optional<Bank> op = bankDao.findById(id); // Optional視為容器，接收Bank(因為id不一定對應的到物件)
		return op.orElse(new Bank());// 確認容器內有物件則回傳，若無則回傳()內物件
	}

	@Override
	public BankResponse deposit(Bank bank) {
		BankResponse result = compareInfo(bank); // 比對帳號密碼的方法
		if (result.getMessage() != "OK") { // 判定比較結果
			return result;
		} else {
			Bank resBank = result.getBank(); // 抓出比對結果
			int newAmount = resBank.getAmount() + bank.getAmount(); // 加總=舊資料庫餘額+存款金額
			resBank.setAmount(newAmount); // 更新舊資料庫存款金額
			return new BankResponse(bankDao.save(resBank), "存款成功!"); // 更新bank資訊
		}
	}

	@Override
	public BankResponse withdraw(Bank bank) {
		BankResponse result = compareInfo(bank); // 比對帳號密碼的方法
		if (result.getMessage() != "OK") { // 判定比較結果
			return result;
		} else {
			if (bank.getAmount() > result.getBank().getAmount()) { // 提款不能大於存款
				return new BankResponse(new Bank(), "提款金額大於餘額!");
			}
			int newAmount = result.getBank().getAmount() - bank.getAmount(); // 加總=舊資料庫餘額+存款金額
			result.getBank().setAmount(newAmount); // 更新舊資料庫存款金額
			return new BankResponse(bankDao.save(result.getBank()), "提款成功!"); // 更新bank資訊
		}
	}

}
