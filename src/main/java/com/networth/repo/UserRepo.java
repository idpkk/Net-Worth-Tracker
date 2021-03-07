package com.networth.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.networth.entity.User;

public interface UserRepo extends CrudRepository<User, Long>{

	Optional<User> findByUsername(String username);
}
