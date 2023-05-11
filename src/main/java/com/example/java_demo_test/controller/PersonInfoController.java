package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.PersonInfoRequest;
import com.example.java_demo_test.vo.PersonInfoResponse;

@RestController
public class PersonInfoController {

	@Autowired
	private PersonInfoService personInfoService;
	
	@PostMapping(value = "add_person_info")
	public PersonInfoResponse addPersonInfo(@RequestBody PersonInfoRequest request) {
		return personInfoService.addPersonInfo(request.getPersonInfoList());
	}

	@GetMapping(value = "get_person_info")
	public PersonInfoResponse getPersonIngo() {
		return personInfoService.getAllPersonInfo();
	}

	@GetMapping(value = "get_person_info_by_id")
	public PersonInfoResponse getPersonInfoById(@RequestBody PersonInfoRequest request) {
		return personInfoService.getPersonInfoById(request.getId());
	}

	@GetMapping(value = "get_person_info_by_age_greater_than")
	public PersonInfoResponse getPersonInfoByAgeGreaterThan(@RequestBody PersonInfoRequest request) {
		return personInfoService.getPersonInfoByAgeGreaterThan(request.getAge());
	}
	
	@GetMapping(value = "get_person_info_by_age_less_than_equal")
	public PersonInfoResponse getPersonInfoByAgeLessThanEqual(@RequestBody PersonInfoRequest request) {
		return personInfoService.getPersonInfoByAgeLessThanEqual(request.getAge());
	}
	
	@GetMapping(value = "get_person_info_by_age_less_between")
	public PersonInfoResponse getPersonInfoByAgeBetween(@RequestBody PersonInfoRequest request) {
		return personInfoService.getPersonInfoByAgeBetween(request.getFromAge(),request.getToAge());
	}

	@GetMapping(value = "get_person_info_city_containing")
	public PersonInfoResponse getPersonInfoCityContaining(@RequestBody PersonInfoRequest request) {
		return personInfoService.getPersonInfoCityContaining(request.getStr());
	}

	@GetMapping(value = "get_person_info_by_age_and_city_containing_order_by_age_desc")
	public PersonInfoResponse getPersonInfoByAgeAndCityContainingOrderByAgeDesc(@RequestBody PersonInfoRequest request) {
		return personInfoService.getPersonInfoByAgeAndCityContainingOrderByAgDesc(request.getAge(),request.getStr());
	}

}
