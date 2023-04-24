package com.example.java_demo_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.PersonInfoResponse;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, String> { // <��ƫ��A, primary key�����A>

	// �۩w�q:��¦�A�j�M����
	// 4.>
	public List<PersonInfo> findByAgeGreaterThan(int age);

	// >=
	public List<PersonInfo> findByAgeGreaterThanEqual(int age);

	// up> x >dwon
	public List<PersonInfo> findByAgeBetween(int age1, int age2);

	// <
	public List<PersonInfo> findByAgeLessThan(int age);

	// <=
	public List<PersonInfo> findByAgeLessThanEqual(int age);

	// 7.Containing
	public List<PersonInfo> findByCityContaining(String str);

	// �Ȼs�ƻݨD
	// 5.<= �B�Ѥp��j
	public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);

	// 6.up> x >dwon �B�Ѥj��p �B���e3��
	public List<PersonInfo> findTop3ByAgeBetweenOrderByAgeDesc(int fromAge, int toAge);

	// 8.�~���j��BCity�]�t �B�~�֥Ѥj��p
	public List<PersonInfo> findByAgeGreaterThanAndCityContainingOrderByAgeDesc(int age, String str);

}
