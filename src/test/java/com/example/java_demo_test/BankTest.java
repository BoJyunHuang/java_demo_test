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
		Bank bank = new Bank("A01", "00000000", 1000); // �s�W��ƨ��Ʈw'bank'
		bankDao.save(bank);
	}

	@Test
	public void addInfoTest() {
		Bank bank = new Bank("A02", "00000000", 1000);
		bankService.addInfo(bank); // ��Ʀ��g�L�P�_�O�_�ŦX�W�w
	}

	@Test
	public void getAmountByIdTest() {
		Bank bank = bankService.getAmountById("A01");
		System.out.println("�b��:" + bank.getAccount() + "�A�l�B:" + bank.getAmount());
	}

	@Test
	public void depositTest() {
		Bank oldBank = bankDao.save(new Bank("AA999", "AA123456@", 2000)); // ���ե�
		Bank newBank = new Bank("AA999", "AA123456@", 3000);
		BankResponse response = bankService.deposit(newBank); // �s��
		// �_���A�T�{�s�ڦ��\�P�_
		Assert.isTrue(response.getBank().getAmount() == (oldBank.getAmount() + newBank.getAmount()), "�s�ڥ���!"); 
		Assert.isTrue(response.getMessage().equals("�s�ڦ��\!"), "�s�ڥ���!");
		bankDao.delete(response.getBank()); // �R�����ռƾ�
	}
	
	@Test
	public void withdrawTest() {
		Bank oldBank = bankDao.save(new Bank("AA999", "AA123456@", 5000)); // ���ե�
		Bank newBank = new Bank("AA999", "AA123456@", 2000); // �s�ڪ��B
		BankResponse response = bankService.withdraw(newBank); // �s��
		// �_���A�T�{�s�ڦ��\�P�_
		Assert.isTrue(response.getBank().getAmount() == (oldBank.getAmount() - newBank.getAmount()), "���ڥ���!"); 
		Assert.isTrue(response.getMessage().equals("���ڦ��\!"), "���ڥ���!");
		bankDao.delete(response.getBank()); // �R�����ռƾ�
	}

}
