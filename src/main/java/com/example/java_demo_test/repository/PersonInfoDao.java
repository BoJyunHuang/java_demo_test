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

	// �m��JPQL
	@Transactional
	@Modifying
	@Query("update PersonInfo p set p.name = :newName where p.id = :newId")
	public int updateNameById(
			@Param("newId") String id,
			@Param("newName") String name);
}
