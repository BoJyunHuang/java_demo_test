package com.example.java_demo_test.service.ifs;

import java.util.List;


import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.PersonInfoResponse;

public interface PersonInfoService {

	// 1.創建個人資訊
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList);

	// 2.取得所有個人資訊
	public PersonInfoResponse getAllPersonInfo();

	// 3.透過id取得對應的個人資訊
	public PersonInfoResponse getPersonInfoById(String id);

	// 4.找出年紀大於輸入條件的所有個人資訊
	public PersonInfoResponse getPersonInfoByAgeGreaterThan(int age);

	// 5.找出年紀小於等於輸入條件的所有個人資訊
	public PersonInfoResponse getPersonInfoByAgeLessThanEqual(int age);

	// 6.找出年紀介於輸入條件的所有個人資訊
	public PersonInfoResponse getPersonInfoByAgeBetween(int fromAge, int toAge);

	// 7.找出city包含某特定字眼的所有個人資訊
	public PersonInfoResponse getPersonInfoCityContaining(String str);

	// 8.找出年紀大於輸入條件以及city包含某特定字眼的所有個人資訊，且年齡由大排到小
	public PersonInfoResponse getPersonInfoByAgeAndCityContainingOrderByAgDesc(int age, String str);

}
