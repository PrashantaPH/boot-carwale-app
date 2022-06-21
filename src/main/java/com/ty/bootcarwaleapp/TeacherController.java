package com.ty.bootcarwaleapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {

	@Autowired
	private TeacherRepository repository;

	@PostMapping("/saveteacher")
	public Teacher saveTeacher(@RequestBody Teacher teacher) {
		return repository.save(teacher);
	}

	@GetMapping("/getteacher/{id}")
	public Teacher getTeacherById(@PathVariable int id) {
		Optional<Teacher> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	@GetMapping("/allteachers")
	public List<Teacher> allTeachers() {
		return repository.findAll();
	}

	@PostMapping("/deleteteacher/{id}")
	public String deleteTeacher(@PathVariable int id) {
		Optional<Teacher> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return "Id Not There in a table ";
		} else {
			repository.deleteById(id);
			return "This Id Successfully deleted...!";
		}
	}

	@GetMapping("/updateteacher/{id}")
	public String updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
		Optional<Teacher> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return "No Id";
		} else {
			repository.save(teacher);
			return "Teacher Details Updated";
		}
	}
}
