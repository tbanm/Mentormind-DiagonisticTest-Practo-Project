package com.test.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.entity.Test;
import com.test.service.TestService;


@Controller

public class TestController {
	
	
	private TestService testService;
	
	
	@Autowired
	public TestController(TestService testService) {
		
		this.testService = testService;
	}
	@GetMapping("/form")
    public String testForm(Model model) {
        model.addAttribute("test", new Test());
        Test test = new Test(Arrays.asList("Blood Test", "Eye Test", "Hearing Test", "ECG", "USG", "CT Scan", "Biopsy", "Gastroscopy", "X Rays", "MRI Scan"));
        model.addAttribute("tests", test.getTests());
        return "testForm";
    }
    @PostMapping("/save")
    public String saveTest(Test test, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
        	byte[] bytes = file.getBytes();
        	Path path = Paths.get(file.getOriginalFilename());
            Files.write(path, bytes);
            test.setImageFileName(file.getOriginalFilename());
            System.out.println("Uploaded File Name: " + file.getOriginalFilename());
            System.out.println("File Size: " + file.getSize() + " bytes");
        }
        testService.saveTest(test);
        ModelAndView modelAndView = new ModelAndView("orderSuccess");
        modelAndView.addObject("successMessage", "Test Ordered and Prescription Uploaded Successfully!");
        return "orderSuccess";
       
    }

}
