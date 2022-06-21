package com.ty.bootcarwaleapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentRepository repository;

	@PostMapping("/savestudent")
	public Student saveStudent(@RequestBody Student student) {
		return repository.save(student);
	}

	@GetMapping("/allstudents")
	public List<Student> allStudents() {
		return repository.findAll();
	}

	@PostMapping("/getstudentby")
	public Student getStudentByID(@RequestParam int id) {
		Optional<Student> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}
	@PostMapping("/deletestudentby")
	public String deleteStudentByid(@RequestParam int id) {
		Optional<Student> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return "id no there";
		} else {
			repository.deleteById(id);
			return "id Successfuly deleted";
		}
	}

	@GetMapping("/updatestudent")
	public String updateStudent(@RequestParam int id, @RequestBody Student student) {
		Optional<Student> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return "no id";
		} else {
			repository.save(student);
			return "update Successfuly ";
		}
	}
}
