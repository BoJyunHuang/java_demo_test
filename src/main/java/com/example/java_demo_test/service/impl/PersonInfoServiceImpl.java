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
		// (���b)�ˬd�Ѽ�
		// 1.�ˬd��J�}�C�O�_��null �� ��
		if (CollectionUtils.isEmpty(personInfoList)) {
			return new PersonInfoResponse("�s�W��T���~!");
		}
		// 2.�ˬd�}�C�����e(id, name, city)�O�_��null �� �šA(age)�O�_�p��0
		List<String> ids = new ArrayList<>();
		for (PersonInfo p : personInfoList) {
			if (!StringUtils.hasText(p.getId()) || !StringUtils.hasText(p.getName()) || p.getAge() < 0
					|| !StringUtils.hasText(p.getCity())) {
				return new PersonInfoResponse("�s�W���e���~!");
			}
			ids.add(p.getId());
		}
		// 3.�ˬd���(id)�O�_�w�s�b
		PersonInfoResponse res = checkExistId(personInfoList, ids);
		// �x�s��T
		return new PersonInfoResponse(personInfoDao.saveAll(res.getPersonInfoList()), res.getMessage());
	}

	@Override
	public PersonInfoResponse getAllPersonInfo() {
		// �^�Ǹ�Ʈw���Ҧ�personInfo��T
		return new PersonInfoResponse(personInfoDao.findAll(), "�M�䦨�\!");
	}

	@Override
	public PersonInfoResponse getPersonInfoById(String id) {
		// (���b)�ˬd�Ѽ�:�ˬd��J�}�C�O�_��null �� ��
		if (!StringUtils.hasText(id)) {
			return new PersonInfoResponse("��J��T���~!");
		}
		// ��Ʈw���d��ؼи��
		Optional<PersonInfo> op = personInfoDao.findById(id);
		// ��Ʈw�L�ӵ����
		if (!op.isPresent()) {
			return new PersonInfoResponse("�L�����!");
		}
		// �^�ǥ��T��T
		return new PersonInfoResponse(op.get(), "�M�䦨�\!");
	}

	@Override
	public PersonInfoResponse getPersonInfoByAgeGreaterThan(int age) {
		// (���b)�ˬd�Ѽ�:�ˬd��J�}�C�O�_��null �� ��
		if (age < 0) {
			return new PersonInfoResponse("��J�~�֤��o���t!");
		}
		List<PersonInfo> res = personInfoDao.findByAgeGreaterThan(age);
		// �Ÿ��
		if (res.isEmpty()) {
			return new PersonInfoResponse("�j��������!");
		}
		// �^�ǥ��T��T
		return new PersonInfoResponse(res, "�M�䦨�\!");
	}

	@Override
	public PersonInfoResponse getPersonInfoByAgeLessThanEqual(int age) {
		// (���b)�ˬd�Ѽ�:�ˬd��J�}�C�O�_��null �� ��
		if (age < 0) {
			return new PersonInfoResponse("��J�~�֤��o���t!");
		}
		List<PersonInfo> res = personInfoDao.findByAgeLessThanEqualOrderByAgeAsc(age);
		// �Ÿ��
		if (res.isEmpty()) {
			return new PersonInfoResponse("�j��������!");
		}
		// �^�ǥ��T��T
		return new PersonInfoResponse(res, "�M�䦨�\!");
	}

	@Override
	public PersonInfoResponse getPersonInfoByAgeBetween(int age, int age2) {
		// (���b)�ˬd�Ѽ�:�ˬd��J�}�C�O�_��null �� ��
		if (age < 0 || age2 < 0) {
			return new PersonInfoResponse("��J�~�֤��o���t!");
		}
		if (age > age2) {
			return new PersonInfoResponse("���J�~�֭n��e��J�~�֤j!");
		}
		List<PersonInfo> res = personInfoDao.findTop3ByAgeBetweenOrderByAgeDesc(age, age2);
		// �Ÿ��
		if (res.isEmpty()) {
			return new PersonInfoResponse("�j��������!");
		}
		// �^�ǥ��T��T
		return new PersonInfoResponse(res, "�M�䦨�\!");
	}

	@Override
	public PersonInfoResponse getPersonInfoCityContaining(String str) {
		// (���b)�ˬd�Ѽ�:�ˬd��J�}�C�O�_��null �� ��
		if (!StringUtils.hasText(str)) {
			return new PersonInfoResponse("��J��T���~!");
		}
		List<PersonInfo> res = personInfoDao.findByCityContaining(str);
		// �Ÿ��
		if (res.isEmpty()) {
			return new PersonInfoResponse("�j��������!");
		}
		// �^�ǥ��T��T
		return new PersonInfoResponse(res, "�M�䦨�\!");
	}

	@Override
	public PersonInfoResponse getPersonInfoByAgeAndCityContaining(int age, String str) {
		// (���b)�ˬd�Ѽ�:�ˬd��J�}�C�O�_��null �� ��
		if (!StringUtils.hasText(str) || age < 0) {
			return new PersonInfoResponse("��J��T���~!");
		}
		List<PersonInfo> res = personInfoDao.findByAgeGreaterThanAndCityContaining(age, str);
		// �Ÿ��
		if (res.isEmpty()) {
			return new PersonInfoResponse("�j��������!");
		}
		// �^�ǥ��T��T
		return new PersonInfoResponse(res, "�M�䦨�\!");
	}

	// �p����k��b�̫᭱
	private PersonInfoResponse checkExistId(List<PersonInfo> personInfoList, List<String> personInfoIds) {
		// 1.�P�_���Ƹ�T
		List<PersonInfo> alreadyExistingInfo = personInfoDao.findAllById(personInfoIds);
		if (alreadyExistingInfo.isEmpty()) {
			return new PersonInfoResponse(personInfoList, "�s����Ʀ��\!");
		}
		// 2.�s�W��T�����w�s�b
		if (alreadyExistingInfo.size() == personInfoList.size()) {
			return new PersonInfoResponse(new ArrayList<PersonInfo>(), "�s����ƥ����w�s�b!");
		}
		// 3.�R���w�s�b��T
		// ��X���ƪ�id
		List<String> alreadyExistingInfoIds = new ArrayList<>();
		for (PersonInfo i : alreadyExistingInfo) {
			alreadyExistingInfoIds.add(i.getId());
		}
		// ���XpersonInfoList�������Ƹ�T
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
		return new PersonInfoResponse(resIds, "�s�W��T�����s�b��Ʈw!");
	}
}
