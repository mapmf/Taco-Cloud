package com.example.demo.form;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationForm {
	
	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	
	public User toUser(PasswordEncoder encoder){
		return new User(username, encoder.encode(password), fullname, street, city, state, zip, phoneNumber);
	}
}
