package com.example.java_demo_test.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.repository.LoginDao;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginResponse;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	// �b������3~8�A���঳�ť�
	private String accountPattern = "\\S{3,8}";
	// �K�X����8~12�A�ܤ֤@�ӯS��Ÿ��A����ťաBTab�B�w��B����B�������Ÿ�
	/*
	 * ^ ��ܤǰt�}�l��m
	 * (?=.*[!@#$%^&*+=])�P�_����ܥ����n�s�b�ܤ֤@�ӯS��Ÿ��A?=���P�_�A���p�A�����Ȧr���e�סA�Ȱ��P�_
	 * (?!.*\\s)�P�_����ܤ���s�b����ťաBTab�B�w��B����B�������Ÿ�
	 * $ ��ܤǰt������m
	 */
	private String passwordPattern = "^(?=.*[!@#$%^&*+=])(?!.*\\s)[\\S]{8,12}$";

	@Override
	public LoginResponse register(Login login) {
		// ���b
		LoginResponse signUp = signUpCheck(login);
		if (signUp.getLogin() == null) {
			return signUp;
		}
		// �ˬd�b���O�_�w�s�b
		Optional<Login> acop = loginDao.findById(login.getAccount());
		if (acop.isPresent()) {
			return new LoginResponse("�b���w�s�b!");
		}
		// ������U�A�������U�ɶ�
		login.setRegisterTime(LocalDateTime.now());
		loginDao.save(login);
		return new LoginResponse(hidePassword(login), "�b�����U���\!");
	}

	@Override
	public LoginResponse activate(Login login) {
		// ���b
		LoginResponse signUp = signInCheck(login);
		if (signUp.getLogin() == null) {
			return signUp;
		}
		// ���b���K�X
		LoginResponse res = accountAndPasswordCheck(login);
		if (res.getLogin() == null) {
			return res;
		}
		// �E���b��
		res.getLogin().setActive(true);
		loginDao.save(res.getLogin());
		return new LoginResponse(hidePassword(login), "�b���ҥΦ��\!");
	}

	@Override
	public LoginResponse signIn(Login login) {
		// ���b
		LoginResponse signUp = signInCheck(login);
		if (signUp.getLogin() == null) {
			return signUp;
		}
		// ���b���K�X
		LoginResponse res = accountAndPasswordCheck(login);
		if (res.getLogin() == null) {
			return res;
		}
		// �T�{�O�_�E��
		if (res.getLogin().isActive() != true) {
			return new LoginResponse("�b�����ҥΡA�бҥαb��!");
		}
		return new LoginResponse("�n�J���\!");
	}

	@Override
	public LoginResponse findUsersByCity(String city) {
		// ���b
		if (!StringUtils.hasText(city)) {
			return new LoginResponse("�d�߫������ର��!");
		}
		// �d����
		List<Login> res = loginDao.findByCityOrderByAgeDesc(city);
		if (res.isEmpty()) {
			return new LoginResponse("�d�L���!");
		}
		// ����������T
		for (Login r:res) {
			r.setPassword("********");
			r.setRegisterTime(null);
		}
		return new LoginResponse(res, "�d�ߦ��\!");
	}

	// ���U:��J��ƽT�{
	private LoginResponse signUpCheck(Login login) {
		// ��J��Ƭ���
		if (login == null) {
			return new LoginResponse("��J��Ƥ��ର��!");
		}
		// ��J���e����
		if (login.getAccount().isEmpty() || login.getPassword().isEmpty() || login.getName().isEmpty()
				|| login.getAge() < 0 || login.getCity().isEmpty()) {
			return new LoginResponse("��J��Ƥ��e�ť�!");
		}
		// �ˬd�b���榡
		if (!login.getAccount().matches(accountPattern)) {
			return new LoginResponse("��J�b�����Ů榡!");
		}
		// �ˬd�K�X�榡
		if (!login.getPassword().matches(passwordPattern)) {
			return new LoginResponse("��J�K�X���Ů榡!");
		}
		return new LoginResponse(login, "��Ƴq�L�ˬd!");
	}

	// �n�J:��J��ƽT�{
	private LoginResponse signInCheck(Login login) {
		// ��J��Ƭ���
		if (login == null) {
			return new LoginResponse("��J��Ƥ��ର��!");
		}
		// ��J���e����
		if (login.getAccount().isEmpty() || login.getPassword().isEmpty()) {
			return new LoginResponse("��J��Ƥ��e�ť�!");
		}
		// �ˬd�b���榡
		if (!login.getAccount().matches(accountPattern)) {
			return new LoginResponse("��J�b�����Ů榡!");
		}
		// �ˬd�K�X�榡
		if (!login.getPassword().matches(passwordPattern)) {
			return new LoginResponse("��J�K�X���Ů榡!");
		}
		return new LoginResponse(login, "��Ƴq�L�ˬd!");
	}

	// �ˬd�b���K�X
	private LoginResponse accountAndPasswordCheck(Login login) {
		Optional<Login> acop = loginDao.findById(login.getAccount());
		if (!acop.isPresent()) {
			return new LoginResponse("�L���b��!");
		}
		if (!acop.get().getPassword().equals(login.getPassword())) {
			return new LoginResponse("�K�X���~!");
		}
		return new LoginResponse(acop.get(), "�b���K�X���T!");
	}
	
	// �B���K�X
	private Login hidePassword(Login login) {
		Login loginInfo = login;
		loginInfo.setPassword("********");
		return loginInfo;
	}

}
