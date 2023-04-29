package com.example.java_demo_test.service.ifs;

import java.util.List;


import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.PersonInfoResponse;

public interface PersonInfoService {

	// 1.�ЫحӤH��T
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList);

	// 2.���o�Ҧ��ӤH��T
	public PersonInfoResponse getAllPersonInfo();

	// 3.�z�Lid���o�������ӤH��T
	public PersonInfoResponse getPersonInfoById(String id);

	// 4.��X�~���j���J���󪺩Ҧ��ӤH��T
	public PersonInfoResponse getPersonInfoByAgeGreaterThan(int age);

	// 5.��X�~���p�󵥩��J���󪺩Ҧ��ӤH��T
	public PersonInfoResponse getPersonInfoByAgeLessThanEqual(int age);

	// 6.��X�~�������J���󪺩Ҧ��ӤH��T
	public PersonInfoResponse getPersonInfoByAgeBetween(int fromAge, int toAge);

	// 7.��Xcity�]�t�Y�S�w�r�����Ҧ��ӤH��T
	public PersonInfoResponse getPersonInfoCityContaining(String str);

	// 8.��X�~���j���J����H��city�]�t�Y�S�w�r�����Ҧ��ӤH��T�A�B�~�֥Ѥj�ƨ�p
	public PersonInfoResponse getPersonInfoByAgeAndCityContainingOrderByAgDesc(int age, String str);

}
