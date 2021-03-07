package com.networth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.networth.service.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService service;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//this is working
		auth.userDetailsService(service)/* .passwordEncoder(encoder()) */;
		
		//this is code from claim-api
		//auth.authenticationProvider(new MyAuthenticationProvider(service.loadUserByUsername(null)));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user")
				.hasAnyRole("USER", "ADMIN").antMatchers("/*").permitAll().and().formLogin();
	}

	@Bean
    public PasswordEncoder encoder() {
        //return new BCryptPasswordEncoder(11);
		return NoOpPasswordEncoder.getInstance();
    }	

}
