package com.example.demo.listeners;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.Authority;
import com.example.demo.model.User;
import com.example.demo.persistence.AuthorityRepository;
import com.example.demo.persistence.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent>{

	private boolean alreadySetup = false;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthorityRepository authorityRepo;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(alreadySetup) {
			return;
		}
		
		Authority roleAdmin = createIfNotExists("ROLE_ADMIN");
		Authority roleUser = createIfNotExists("ROLE_USER");
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = new User();
		user.setUsername("teste");
		user.setPassword(encoder.encode("123"));
		user.setAuthorities(Arrays.asList(roleAdmin, roleUser));
		user.setEnabled(true);
		
		userRepo.save(user);
		
		alreadySetup = true;
	}

	private Authority createIfNotExists(String string) {
		
		Authority authority = authorityRepo.findByName(string);
		
		if(authority == null) {
			authority = new Authority();
			authority.setName(string);
			
			authority = authorityRepo.save(authority);
		}
		
		return authority;
	}

}
