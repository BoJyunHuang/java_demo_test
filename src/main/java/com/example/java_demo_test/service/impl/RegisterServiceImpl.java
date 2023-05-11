package com.example.java_demo_test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.repository.RegisterDao;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDao registerDao;

	@Override
	public RegisterResponse register(String account, String pwd) {
		// ���b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("incorrect");
		}
		// �ˬd�b���O�_�w�s�b
		if (registerDao.existsById(account)) {
			return new RegisterResponse("incorrect");
		}
		// ������U�A�������U�ɶ�
		registerDao.save(new Register(account, pwd));
		return new RegisterResponse("success");
	}

	@Override
	public RegisterResponse avtive(String account, String pwd) {
		// ���b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("incorrect");
		}
		// �ˬd�b���O�_�w�s�b
		Register res = registerDao.findByAccountAndPwd(account, pwd);
		if (res == null) {
			return new RegisterResponse("not found");
		}
		// �ҥαb��
		res.setActive(true);
		registerDao.save(res);
		return new RegisterResponse("success");
	}

	@Override
	public RegisterResponse login(String account, String pwd) {
		// ���b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("incorrect");
		}
		// �ˬd�b���O�_�w�s�b(�ҥ�)
		Register res = registerDao.findByAccountAndPwdAndIsActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("login failed");
		}
		return new RegisterResponse("success");
	}

	@Override
	public RegisterResponse getRegTime(String account, String pwd) {
		// �ˬd�b���O�_�w�s�b(�ҥ�)
		Register res = registerDao.findByAccountAndPwdAndIsActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("login failed");
		}
		// �^�ǵ��U�ɶ�
		return new RegisterResponse(res.getRegTime(), "success");
	}

	@Override
	public RegisterResponse getRegTime2(RegisterRequest request, String account, String pwd, Integer verifyCode) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("Please login");
		}
		if (verifyCode == null || verifyCode != request.getVeryfyCode()) {
			return new RegisterResponse("Verify code incorrect");
		}
		// �ˬd�b���O�_�w�s�b(�ҥ�)
		Register res = registerDao.findByAccountAndPwdAndIsActive(account, pwd, true);
		if (res == null) {
			return new RegisterResponse("login failed");
		}
		// �^�ǵ��U�ɶ�
		return new RegisterResponse(res.getRegTime(), "success");
	}

}
