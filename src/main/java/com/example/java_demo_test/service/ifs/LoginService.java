package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

public interface LoginService {

	// ���U�b��
	public LoginResponse register(LoginRequest request);

	// �E���b��
	public LoginResponse activate(String account, String password);

	// �n�J(�ˬd�b���B�K�X�B�b���O�_�E��)
	public LoginResponse signIn(String account, String password);

	// �H�������`�M�u����M�ϥΪ̡A�^�Ǥ���t�K�X�A�t�ƧǨ̷Ӧ~��
	public LoginResponse findUsersByCity(String city);
}
