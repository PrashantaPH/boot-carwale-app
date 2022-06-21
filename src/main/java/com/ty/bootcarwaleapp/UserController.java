package com.ty.bootcarwaleapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@PostMapping("/saveuser")
	public User saveUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@GetMapping("/getuserby")
	public User getUserById(@RequestParam int id) {
		Optional< User> optional=repository.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	@GetMapping("/allusers")
	public List<User> allUsers(){
		return repository.findAll();
	}
	
	@PostMapping("/deleteuser")
	public String deleteUser(@RequestParam int id) {
		Optional< User> optional=repository.findById(id);
		if(optional.isEmpty()) {
			return "No Id";
		}else {
			repository.deleteById(id);
			return "User details Deleted";
		}
	}
	
	@PostMapping("/updateuser")
	public String updateUser(@RequestParam int id,@RequestBody User user) {
		Optional<User> optional=repository.findById(id);
		if(optional.isEmpty()) {
			return "no id";
			}else {
				repository.save(user);
				return "User Updated";
			}
	}
	
	@GetMapping("/userbyemail")
	public User getUserByEmail(@RequestParam String email) {
		List<User> optional=repository.findByEmail(email);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get(0);
		}
	}
	
	@GetMapping("/userbyphone")
	public User getUserByPhone(@RequestParam long phone) {
		List<User> users=repository.findByPhone(phone);
		if(users.isEmpty()) {
			return null;
		}else {
			return users.get(0);
		}
	}
	
	@GetMapping("/userbyrole/{role}")
	public User getUserByRole(@PathVariable String role) {
		List<User> users=repository.findByRole(role);
		if(users.isEmpty()) {
			return null;
		}else
			return users.get(0);
	}
	
	@GetMapping("/userby/{phone}")
	public User userByPhone(@PathVariable long phone) {
		List<User> users=repository.findByPhone(phone);
		if(users.isEmpty()) {
			return null;
		}else
			return users.get(0);
	}
	
	//its not a good practies why becouse @ its not a good practies use int url
	@GetMapping("/useremail/{email}")
	public List<User> userByEmail(@PathVariable String email){
		List<User> users =repository.findByEmail(email);
		if(users.isEmpty()) {
			return null;
		}else
			return users;
	}
	
	@GetMapping("/usergenderrole")//-->http://localhost:8080/usergenderrole?gender=male&role=trenee
	public List<User> getUserByGenderAndRole(@RequestParam String gender,@RequestParam String  role){
		List<User> users=repository.getData(gender, role);
		if(users.isEmpty()) {
			return null;
		}else
			return users;
	}
	
	@GetMapping("/uservalidate")//--->http://localhost:8080/uservalidate?email=ph@gmail.com&password=p123
	public List<User> validateUser(@RequestParam String email,@RequestParam String password){
		List<User> users=repository.validate(email, password);
		if(users.isEmpty()) {
			return null;
		}else
			return users;
	}
}
