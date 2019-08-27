package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/* 
		 * In memory security
		 */
		/*auth.inMemoryAuthentication()
					.withUser("buzz")
					.password("{noop}123")
					.authorities("ROLE_USER")
					.and()
					.withUser("woody")
					.password("{noop}bullseye")
					.authorities("ROLE_USER");*/
		
		/*
		 * JDBC security
		 */
		auth
		.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username, password, enabled from Users where username=?")
			.authoritiesByUsernameQuery("select u.username, a.name from User_Authority ua "
					+ "inner join Users u on u.id = ua.user_id "
					+ "inner join Authority a on ua.authorities_id = a.id where u.username=?")
			.passwordEncoder(new BCryptPasswordEncoder());
	}

}