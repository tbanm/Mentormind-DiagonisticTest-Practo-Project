package com.test.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Test")
public class Test {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String patientName;
	
	private List<String> tests;
	private String testName;
	
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}




	private int age;
    private String address;
    private String phoneNumber;
   
    private String imageFileName;

	public Test() {
		
	}
	
	public Test(List<String> tests) {
		
		
		this.tests = tests;
		
	}



	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getPatientName() {
		return patientName;
	}




	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}




	public List<String> getTests() {
		return tests;
	}




	public void setTests(List<String> tests) {
		this.tests = tests;
	}




	public int getAge() {
		return age;
	}




	public void setAge(int age) {
		this.age = age;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}




	public String getImageFileName() {
		return imageFileName;
	}




	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}




    

}
