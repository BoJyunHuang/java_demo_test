package com.example.java_demo_test.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.PersonInfo;

public class PersonInfoDaoImpl extends BaseDao {

	// BaseDao����
	public List<PersonInfo> doQueryByAge(int age) {
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :inputAge");
		Map<String, Object> params = new HashMap<>();
		params.put("inputAge", age);
		return doQuery(sb.toString(), params, PersonInfo.class);
	}

	// �����
	public List<PersonInfo> doQueryByAge(int age, int limit) {
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :inputAge");
		Map<String, Object> params = new HashMap<>();
		params.put("inputAge", age);
		return doQuery(sb.toString(), params, PersonInfo.class, limit);
	}

	// �����d��
	public List<PersonInfo> doQueryByAge(int age, int limit, int startPosition) {
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :inputAge");
		Map<String, Object> params = new HashMap<>();
		params.put("inputAge", age);
		return doQuery(sb.toString(), params, PersonInfo.class, limit, startPosition);
	}

	// ��s��k
	public int updateAgeByName(String name, int age) {
		StringBuffer sb = new StringBuffer();
		sb.append("update PersonInfo set age = :age where name >= :name");
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		params.put("age", age);
		return doUpdate(sb.toString(), params);
	}

}
