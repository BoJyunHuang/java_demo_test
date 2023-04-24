package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.vo.LoginResponse;

public interface LoginService {

	// ���U�b��
	public LoginResponse register(Login login);

	// �E���b��
	public LoginResponse activate(Login login);

	// �n�J(�ˬd�b���B�K�X�B�b���O�_�E��)
	public LoginResponse signIn(Login login);

	// �H�������`�M�u����M�ϥΪ̡A�^�Ǥ���t�K�X�A�t�ƧǨ̷Ӧ~��
	public LoginResponse findUsersByCity(String city);
}
