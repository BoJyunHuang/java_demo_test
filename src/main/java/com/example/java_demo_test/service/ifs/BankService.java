package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.vo.BankResponse;

public interface BankService {

	// �s�W���
	public void addInfo(Bank bank); // ��@��k�~�ݭn�j�A��

	// ���X�l�B
	public Bank getAmountById(String id);

	// �s��
	public BankResponse deposit(Bank bank);
	
	// ����
	public BankResponse withdraw(Bank bank);
	
}
