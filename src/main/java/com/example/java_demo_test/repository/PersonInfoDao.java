package com.example.java_demo_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.java_demo_test.entity.PersonInfo;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, String> { // <資料型態, primary key的型態>

	// 自定義:基礎，搜尋條件
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
	public List<PersonInfo> findTop3ByAgeBetweenOrderByAgeDesc(int fromAge, int toAge);

	// 8.年紀大於且City包含 且年齡由大到小
	public List<PersonInfo> findByAgeGreaterThanAndCityContainingOrderByAgeDesc(int age, String str);

	// 練習JPQL
	@Transactional
	@Modifying
	@Query("update PersonInfo p set p.name = :newName where p.id = :newId")
	public int updateNameById(
			@Param("newId") String id,
			@Param("newName") String name);
}
