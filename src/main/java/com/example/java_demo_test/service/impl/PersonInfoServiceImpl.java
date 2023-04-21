package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.repository.PersonInfoDao;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.PersonInfoRequest;
import com.example.java_demo_test.vo.PersonInfoResponse;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList) {
		// (防呆)檢查參數
		// 1.檢查輸入陣列是否為null 或 空
		if (CollectionUtils.isEmpty(personInfoList)) {
			return new PersonInfoResponse("新增資訊錯誤!");
		}
		// 2.檢查陣列中內容(id, name, city)是否為null 或 空，(age)是否小於0
		List<String> ids = new ArrayList<>();
		for (PersonInfo p : personInfoList) {
			if (!StringUtils.hasText(p.getId()) || !StringUtils.hasText(p.getName()) || p.getAge() < 0
					|| !StringUtils.hasText(p.getCity())) {
				return new PersonInfoResponse("新增內容錯誤!");
			}
			ids.add(p.getId());
		}
		// 3.檢查資料(id)是否已存在
		PersonInfoResponse res = checkExistId(personInfoList, ids);
		// 儲存資訊
		return new PersonInfoResponse(personInfoDao.saveAll(res.getPersonInfoList()), res.getMessage());
	}

	@Override
	public PersonInfoResponse getAllPersonInfo() {
		// 回傳資料庫中所有personInfo資訊
		return new PersonInfoResponse(personInfoDao.findAll(), "尋找成功!");
	}

	@Override
	public PersonInfoResponse getPersonInfoById(String id) {
		// (防呆)檢查參數:檢查輸入陣列是否為null 或 空
		if (!StringUtils.hasText(id)) {
			return new PersonInfoResponse("輸入資訊錯誤!");
		}
		// 資料庫中查找目標資料
		Optional<PersonInfo> op = personInfoDao.findById(id);
		// 資料庫無該筆資料
		if (!op.isPresent()) {
			return new PersonInfoResponse("無此資料!");
		}
		// 回傳正確資訊
		return new PersonInfoResponse(op.get(), "尋找成功!");
	}

	@Override
	public PersonInfoResponse getPersonInfoByAgeGreaterThan(int age) {
		// (防呆)檢查參數:檢查輸入陣列是否為null 或 空
		if (age < 0) {
			return new PersonInfoResponse("輸入年齡不得為負!");
		}
		List<PersonInfo> res = personInfoDao.findByAgeGreaterThan(age);
		// 空資料
		if (res.isEmpty()) {
			return new PersonInfoResponse("搜索不到資料!");
		}
		// 回傳正確資訊
		return new PersonInfoResponse(res, "尋找成功!");
	}

	@Override
	public PersonInfoResponse getPersonInfoByAgeLessThanEqual(int age) {
		// (防呆)檢查參數:檢查輸入陣列是否為null 或 空
		if (age < 0) {
			return new PersonInfoResponse("輸入年齡不得為負!");
		}
		List<PersonInfo> res = personInfoDao.findByAgeLessThanEqualOrderByAgeAsc(age);
		// 空資料
		if (res.isEmpty()) {
			return new PersonInfoResponse("搜索不到資料!");
		}
		// 回傳正確資訊
		return new PersonInfoResponse(res, "尋找成功!");
	}

	@Override
	public PersonInfoResponse getPersonInfoByAgeBetween(int age, int age2) {
		// (防呆)檢查參數:檢查輸入陣列是否為null 或 空
		if (age < 0 || age2 < 0) {
			return new PersonInfoResponse("輸入年齡不得為負!");
		}
		if (age > age2) {
			return new PersonInfoResponse("後輸入年齡要比前輸入年齡大!");
		}
		List<PersonInfo> res = personInfoDao.findTop3ByAgeBetweenOrderByAgeDesc(age, age2);
		// 空資料
		if (res.isEmpty()) {
			return new PersonInfoResponse("搜索不到資料!");
		}
		// 回傳正確資訊
		return new PersonInfoResponse(res, "尋找成功!");
	}

	@Override
	public PersonInfoResponse getPersonInfoCityContaining(String str) {
		// (防呆)檢查參數:檢查輸入陣列是否為null 或 空
		if (!StringUtils.hasText(str)) {
			return new PersonInfoResponse("輸入資訊錯誤!");
		}
		List<PersonInfo> res = personInfoDao.findByCityContaining(str);
		// 空資料
		if (res.isEmpty()) {
			return new PersonInfoResponse("搜索不到資料!");
		}
		// 回傳正確資訊
		return new PersonInfoResponse(res, "尋找成功!");
	}

	@Override
	public PersonInfoResponse getPersonInfoByAgeAndCityContaining(int age, String str) {
		// (防呆)檢查參數:檢查輸入陣列是否為null 或 空
		if (!StringUtils.hasText(str) || age < 0) {
			return new PersonInfoResponse("輸入資訊錯誤!");
		}
		List<PersonInfo> res = personInfoDao.findByAgeGreaterThanAndCityContaining(age, str);
		// 空資料
		if (res.isEmpty()) {
			return new PersonInfoResponse("搜索不到資料!");
		}
		// 回傳正確資訊
		return new PersonInfoResponse(res, "尋找成功!");
	}

	// 私有方法放在最後面
	private PersonInfoResponse checkExistId(List<PersonInfo> personInfoList, List<String> personInfoIds) {
		// 1.判斷重複資訊
		List<PersonInfo> alreadyExistingInfo = personInfoDao.findAllById(personInfoIds);
		if (alreadyExistingInfo.isEmpty()) {
			return new PersonInfoResponse(personInfoList, "新曾資料成功!");
		}
		// 2.新增資訊全部已存在
		if (alreadyExistingInfo.size() == personInfoList.size()) {
			return new PersonInfoResponse(new ArrayList<PersonInfo>(), "新曾資料全部已存在!");
		}
		// 3.刪除已存在資訊
		// 抓出重複的id
		List<String> alreadyExistingInfoIds = new ArrayList<>();
		for (PersonInfo i : alreadyExistingInfo) {
			alreadyExistingInfoIds.add(i.getId());
		}
		// 取出personInfoList的不重複資訊
		List<PersonInfo> resIds = new ArrayList<>();
		for (PersonInfo p : personInfoList) {
			if (!alreadyExistingInfoIds.contains(p.getId())) {
				resIds.add(p);
			}
		}
		/*
		 * List<PersonInfo> saveList = personInfoList.stream().filter(item ->
		 * !existId.contains(item.getId())).collect(Collectors.toList());
		 */
		return new PersonInfoResponse(resIds, "新增資訊部分存在資料庫!");
	}
}
