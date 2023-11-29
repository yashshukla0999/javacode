package com.example.blog.controllers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.UserDto;
import com.example.blog.services.UserService;
@RestController
@RequestMapping("/api/users")
public class controllers {
	@Autowired
	public UserService userservice;
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto){
		UserDto createUserDto= this.userservice.createUser(userdto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) {
		UserDto updateUser = this.userservice.updateUser(userDto, id);
		return ResponseEntity.ok(updateUser);
	}
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id){
		this.userservice.deleteuser(id);
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
		return ResponseEntity.ok(this.userservice.getUserById(id));
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
	    List<UserDto> userDtos = this.userservice.getAllUser();
	    return ResponseEntity.ok(userDtos);
	}

	
	

}
