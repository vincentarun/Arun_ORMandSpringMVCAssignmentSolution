package com.greatlearning.studentmanagement.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.studentmanagement.model.Student;
import com.greatlearning.studentmanagement.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String getAllStudents (Model theModel) {
		System.out.println("request received");
		
		List<Student> theStudents = studentService.findAll();
		
		theModel.addAttribute("Students", theStudents);
		
		return "studentslist";
	}

	@RequestMapping("/add")
	public String addStudent(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("Student", theStudent);
		return "savestudent";
	}

	@RequestMapping("/update")
	public String updateStudent(@RequestParam("id") int theId,Model theModel) {
		
		Student theStudent = studentService.findById(theId);
										
		theModel.addAttribute("Student", theStudent);
		
		return "savestudent";
		   
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course, 
			@RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;
		if(id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		}else 
			
			theStudent = new Student(firstName,lastName,course,country);
		
		studentService.save(theStudent);
		
		return "redirect:/students/list";
	}
	
	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("id") int id) {
		
		studentService.deleteById(id);
		
		return "redirect:/students/list";
	}
}


