package com.mycomp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mycomp.service.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailService() {

		return new UserDetailServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder getEncoder() {

		return new BCryptPasswordEncoder();
	}

	/* AUTENTICACION */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailService()).passwordEncoder(getEncoder());

	}

	/* AUTORIZACION */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		        .antMatchers("/").permitAll()
		        .antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/ModuloProductos/**").hasRole("ADMIN")
		        .antMatchers("/productos/**").hasRole("ADMIN");

		http.authorizeRequests().and()
		.formLogin().loginPage("/usuario/login")
		.defaultSuccessUrl("/usuario/acceder");

	}

}
