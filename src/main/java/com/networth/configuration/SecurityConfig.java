package com.networth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.networth.service.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService service;

	/*
	 * @Autowired JwtAuthFilter jwtFilter;
	 */

	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// this is working
		auth.userDetailsService(service)/* .passwordEncoder(encoder()) */;
		// this is code from claim-api
		// auth.authenticationProvider(new MyAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll()
				.antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasAnyRole("USER", "ADMIN").anyRequest()
				.authenticated()
		/*
		 * .and().sessionManagement()
		 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		 * .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		 */
		;

	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
		// return NoOpPasswordEncoder.getInstance();
	}

}
