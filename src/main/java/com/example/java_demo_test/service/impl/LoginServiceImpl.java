package com.example.java_demo_test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.repository.LoginDao;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	// �b������3~8�A���঳�ť�
	private String accountPattern = "\\S{3,8}";
	// �K�X����8~12�A�ܤ֤@�ӯS��Ÿ��A����ťաBTab�B�w��B����B�������Ÿ�
	/*
	 * ^ ��ܤǰt�}�l��m�A(?=.*[!@#$%^&*+=])�P�_����ܥ����n�s�b�ܤ֤@�ӯS��Ÿ��A?=���P�_�A���p�A�����Ȧr���e�סA�Ȱ��P�_
	 * (?!.*\\s)�P�_����ܤ���s�b����ťաBTab�B�w��B����B�������Ÿ��A$ ��ܤǰt������m
	 */
	private String passwordPattern = "^(?=.*[\\S^\\w])[\\S]{8,12}$";

	@Override
	public LoginResponse register(LoginRequest register) {
		// ���b
		LoginResponse signUp = signUpCheck(register);
		if (signUp.getRequest() == null) {
			return signUp;
		}
		// �ˬd�b���O�_�w�s�b
		boolean reop = loginDao.existsById(register.getAccount());
		if (reop) {
			return new LoginResponse(RtnCode.ALREADY_EXISTED.getMessage());
		}
		// ������U�A�������U�ɶ�
		Login login = new Login(register.getAccount(), register.getPassword(), register.getName(), register.getAge(),
				register.getCity());
		loginDao.save(login);
		// ���ñK�X�A�����
		login.setPassword(null);
		return new LoginResponse(login, RtnCode.SUCCESSFULLY_REPLY.getMessage());
	}

	@Override
	public LoginResponse activate(String account, String password) {
		// �n�J�T�{
		LoginResponse res = signInCheck(account, password);
		if (res.getLogin() == null) {
			return res;
		}
		// �E���b��
		res.getLogin().setActive(true);
		loginDao.save(res.getLogin());
		// ���ñK�X�A�����
		res.getLogin().setPassword(null);
		return new LoginResponse(res.getLogin(), RtnCode.SUCCESSFULLY_REPLY.getMessage());
	}

	@Override
	public LoginResponse signIn(String account, String password) {
		// �n�J�T�{
		LoginResponse res = signInCheck(account, password);
		if (res.getLogin() == null) {
			return res;
		}
		// �T�{�O�_�E��
		if (res.getLogin().isActive() != true) {
			return new LoginResponse("�b�����ҥΡA�бҥαb��!");
		}
		return new LoginResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public LoginResponse findUsersByCity(String city) {
		// ���b
		if (!StringUtils.hasText(city)) {
			return new LoginResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// �d����
		List<Login> res = loginDao.findByCityOrderByAgeDesc(city);
		if (res.isEmpty()) {
			return new LoginResponse(RtnCode.ALREADY_EXISTED.getMessage());
		}
		// ����������T
		for (Login r : res) {
			r.setPassword(null);
			r.setRegisterTime(null);
		}
		return new LoginResponse(res, RtnCode.SUCCESSFULLY_REPLY.getMessage());
	}

	// ���U:��J��ƽT�{
	private LoginResponse signUpCheck(LoginRequest register) {
		// �n�J:��J��ƽT�{
		if (register == null) {
			return new LoginResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// ���U��Ƥ��e�T�{
		if (!StringUtils.hasText(register.getAccount()) || !StringUtils.hasText(register.getPassword())
				|| !StringUtils.hasText(register.getName()) || register.getAge() < 0
				|| !StringUtils.hasText(register.getCity())) {
			return new LoginResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// �ˬd�b���榡
		if (!register.getAccount().matches(accountPattern)) {
			return new LoginResponse(RtnCode.PATTERNISNOTMATCH.getMessage());
		}
		// �ˬd�K�X�榡
		if (!register.getPassword().matches(passwordPattern)) {
			return new LoginResponse(RtnCode.PATTERNISNOTMATCH.getMessage());
		}
		return new LoginResponse(register, RtnCode.SUCCESSFULLY_REPLY.getMessage());
	}

	// �n�J�ˬd
	private LoginResponse signInCheck(String account, String password) {
		// ���b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(password)) {
			return new LoginResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// ���b���K�X
		Login res = loginDao.findByAccountAndPassword(account, password);
		if (res == null) {
			return new LoginResponse(RtnCode.INCORRECT.getMessage());
		}
		return new LoginResponse(res, RtnCode.SUCCESSFULLY_REPLY.getMessage());
	}

}
