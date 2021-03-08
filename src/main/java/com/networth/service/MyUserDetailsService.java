package com.networth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.networth.entity.User;
import com.networth.exception.NewtworthException;
import com.networth.model.MyUserDetails;
import com.networth.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username 2:  " + username);
		Optional<User> user = repo.findByUsername(username);
		if (!user.isPresent()) {
			System.out.println("USERNMAE:   " + username);
			throw new NewtworthException("USERNMAE Not Found: " + username);
		}
		System.out.println("password 3:  " + user.get().getPassword());
		return user.map(MyUserDetails::new).get();
	}

	public User signup(User user) {
		user.setPassword(getEncodedPassword(user.getPassword()));
		user.setActive(true);
		return repo.save(user);
	}

	private String getEncodedPassword(String password) {
		return new BCryptPasswordEncoder(11).encode(password);

	}

}
