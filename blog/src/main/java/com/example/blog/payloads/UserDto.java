package com.example.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
	private int id;
	@NotNull
	private String name;

@Email
	private String Email;
@NotEmpty
	private String password;
@NotEmpty
	private String about;

}
