package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.test.entity.Test;
import com.test.repository.TestRepository;

@Service
public class TestService {
	
	@Autowired
	private TestRepository testRepository;
	
	public List<Test> getAllTests() {
        return testRepository.findAll();
    }
    public Optional<Test> getTestById(Long id) {
        return testRepository.findById(id);
    }
    public void saveTest(Test test) {
        testRepository.save(test);
    }
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
	

}
