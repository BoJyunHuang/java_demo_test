package com.example.java_demo_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.PersonInfoResponse;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, String> { // <資料型態, primary key的型態>

	// 自定義:搜尋大於輸入條件的資料
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

	// 客製化需求
	// 5.<= 且由小到大
	public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);

	// 6.up> x >dwon 且由大到小 且取前3筆
	public List<PersonInfo> findTop3ByAgeBetweenOrderByAgeDesc(int age, int anotherAge);

	// 8.年紀大於且City包含
	public List<PersonInfo> findByAgeGreaterThanAndCityContaining(int age, String str);

}
