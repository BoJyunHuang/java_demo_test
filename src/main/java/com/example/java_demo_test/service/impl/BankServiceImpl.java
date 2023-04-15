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

	private String accountPattern = "\\w{3,8}"; // �b�����W��F��
	private String pwdPattern = "\\S{8,16}"; // �K�X���W��F��

	@Autowired
	private BankDao bankDao;

	// �ˬd����T���T�P�_����k
	private boolean checkInfo(String info, String item, String pattern) {
		if (item == null) { // ����줣�o����
			System.out.println(info + "����!");
			return false;
		} else if (!item.matches(pattern)) { // ����T���ŦX����
			System.out.println(info + "�������ŦX�W�w!");
			return false;
		}
		return true; // �ŦX�����^��true
	}

	// ���b���K�X���T�P�_����k
	private BankResponse compareInfo(Bank bank) {
		if (bank == null || !StringUtils.hasText(bank.getAccount()) || !StringUtils.hasText(bank.getPassword())
				|| bank.getAmount() <= 0) { // �ˬd��J����O�_����
			return new BankResponse(new Bank(), "�b��/�K�X���~!");
		}
		Bank resBank = bankDao.findByAccountAndPassword(bank.getAccount(), bank.getPassword()); // �M�䪫��O�_�s�b�ܮw
		if (resBank == null) { // �Y�M�䤣��A�^��new Bank()
			return new BankResponse(new Bank(), "��Ƥ��s�b!");
		}
		return new BankResponse(resBank, "OK"); // �^�ǥ��T�b��
	}

	@Override
	public void addInfo(Bank bank) {
		// ��ƨ��b
		if (bank == null) {
			System.out.println("��Ƭ���!");
			return; // ���X��k
		}
		// �ˬd�b�����׬O�_�ŦX�W�w�G(1)����3~8 (2)�b�����঳�ť� (3)�b�����঳�S��Ÿ��p~!@#$%^&*
		if (!checkInfo("�b��", bank.getAccount(), accountPattern)) {
			return; // ���X��k
		}
		// �ˬd�K�X���঳�ťթΥ����ťաA�B���׬�8~16
		if (!checkInfo("�K�X", bank.getPassword(), pwdPattern)) {
			return; // ���X��k
		}
		// �ˬd�b��l�B���o���t
		if (bank.getAmount() < 0) { // �l�B�p��0
			System.out.println("�l�B����!");
			return; // ���X��k
		}
		// �s�W��ƨ��Ʈw'bank'
		bankDao.save(bank);

	}

	@Override
	public Bank getAmountById(String id) {
		if (!StringUtils.hasText(id)) { // �ˬd��Jid�O�_null�ΪŦr��
			return new Bank();
		}
		Optional<Bank> op = bankDao.findById(id); // Optional�����e���A����Bank(�]��id���@�w�������쪫��)
		return op.orElse(new Bank());// �T�{�e����������h�^�ǡA�Y�L�h�^��()������
	}

	@Override
	public BankResponse deposit(Bank bank) {
		BankResponse result = compareInfo(bank); // ���b���K�X����k
		if (result.getMessage() != "OK") { // �P�w������G
			return result;
		} else {
			Bank resBank = result.getBank(); // ��X��ﵲ�G
			int newAmount = resBank.getAmount() + bank.getAmount(); // �[�`=�¸�Ʈw�l�B+�s�ڪ��B
			resBank.setAmount(newAmount); // ��s�¸�Ʈw�s�ڪ��B
			return new BankResponse(bankDao.save(resBank), "�s�ڦ��\!"); // ��sbank��T
		}
	}

	@Override
	public BankResponse withdraw(Bank bank) {
		BankResponse result = compareInfo(bank); // ���b���K�X����k
		if (result.getMessage() != "OK") { // �P�w������G
			return result;
		} else {
			if (bank.getAmount() > result.getBank().getAmount()) { // ���ڤ���j��s��
				return new BankResponse(new Bank(), "���ڪ��B�j��l�B!");
			}
			int newAmount = result.getBank().getAmount() - bank.getAmount(); // �[�`=�¸�Ʈw�l�B+�s�ڪ��B
			result.getBank().setAmount(newAmount); // ��s�¸�Ʈw�s�ڪ��B
			return new BankResponse(bankDao.save(result.getBank()), "���ڦ��\!"); // ��sbank��T
		}
	}

}
