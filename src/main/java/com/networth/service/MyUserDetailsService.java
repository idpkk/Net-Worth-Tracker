package com.networth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.networth.constants.Roles;
import com.networth.entity.User;
import com.networth.model.MyUserDetails;
import com.networth.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repo.findByUsername(username);
		if (!user.isPresent()) {
			System.out.println("USERNMAE:   " + username);
			new UsernameNotFoundException("Not Found: " + username);
		}
		return user.map(MyUserDetails::new).get();
	}

	public User signup(User user) {
		user.setActive(true);
		return repo.save(user);
	}

}
