package com.networth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.networth.entity.User;
import com.networth.model.NetWorth;
import com.networth.pojo.AssetDebtRequest;
import com.networth.pojo.LastYearDetails;
import com.networth.service.MyUserDetailsService;
import com.networth.service.NetWorthService;

@RestController
public class NetWorthController {

	@Autowired
	NetWorthService service;

	@Autowired
	MyUserDetailsService userService;
	
	@PostMapping("/addAsset/{id}")
	private String addAsset(@RequestBody @Valid AssetDebtRequest ad, @PathVariable String id) {
		return service.addAsset(ad, id);

	}

	@GetMapping("/netWorth/{id}")
	private NetWorth getNetWorth(@PathVariable String id) {
		return service.getNetWorth(id);
	}

	@PostMapping("/lastYear")
	private NetWorth lastYearDetails(@RequestBody @Valid LastYearDetails lastYearDetails) {
		System.out.println("lastYearDetails: " + lastYearDetails);
		return service.lastYearDetails(lastYearDetails);
	}
	
	@PostMapping("/signup")
	private User signUp(@RequestBody User user) {
		return userService.signup(user);
	}
	
	@GetMapping("/signup")
	private String getUser() {
		return "existing";
	}
	
	
	@GetMapping("/")
	private String home() {
		return "home";
	}
	
	@GetMapping("/user")
	private String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	private String admin() {
		return "admin";
	}
}
