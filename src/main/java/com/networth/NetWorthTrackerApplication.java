package com.networth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.networth.repo.UserRepo;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepo.class)
public class NetWorthTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetWorthTrackerApplication.class, args);
	}

}
