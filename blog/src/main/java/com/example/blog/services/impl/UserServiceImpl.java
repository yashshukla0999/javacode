package com.example.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.exeptions.*;
import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserRepo userRepo;
	@Autowired
	public ModelMapper modelmapper;

	@Override
	public UserDto createUser(@Valid UserDto yash) {
		User user = this.dtoToUser(yash);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser) ;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user =this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updated=this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updated);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user =this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
		return this.userToDto(user);
	}

	public List<UserDto> getAllUser() {
	    List<User> users = userRepo.findAll();
	    List<UserDto> userDtos = new ArrayList<>();

	    for (User user : users) {
	        userDtos.add(userToDto(user));
	    }

	    return userDtos;
	}


	@Override
	public void deleteuser(Integer id) {
		User user =this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
		this.userRepo.delete(user); 

	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user = this.modelmapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
public UserDto userToDto(User user) {
		
	UserDto userDto = this.modelmapper.map(user, UserDto.class);
//	userDto.setId(user.getId());
//	userDto.setName(user.getName());
//	userDto.setEmail(user.getEmail());
//	userDto.setAbout(user.getAbout());
//	userDto.setPassword(user.getPassword());
		
		return userDto;
	}



                    

}
