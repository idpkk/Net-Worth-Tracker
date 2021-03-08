package com.networth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.networth.entity.User;
import com.networth.model.NetWorth;
import com.networth.pojo.AssetDebtRequest;
import com.networth.pojo.AuthenticateRequest;
import com.networth.pojo.AuthenticateResponse;
import com.networth.pojo.LastYearDetails;
import com.networth.service.MyUserDetailsService;
import com.networth.service.NetWorthService;
import com.networth.util.JwtUtil;

@RestController
public class NetWorthController {

	// @Resource(name = "authenticationManager")
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	NetWorthService service;

	@Autowired
	MyUserDetailsService userService;

	@Autowired
	JwtUtil jwtUtils;

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
		return service.lastYearDetails(lastYearDetails);
	}

	@PostMapping("/signup")
	private User signUp(@RequestBody User user) {
		return userService.signup(user);
	}

	@PostMapping("/authenticate")
	private AuthenticateResponse login(@RequestBody() AuthenticateRequest user, final HttpServletRequest request)
			throws Exception {
		try {
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getUsername(),
					user.getPassword());
			Authentication auth = authManager.authenticate(authReq);

			/*
			 * SecurityContext sc = SecurityContextHolder.getContext();
			 * sc.setAuthentication(auth); HttpSession session = request.getSession(true);
			 * session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
			 */
		}

		catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or password", e);
		}

		final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
		String jwt = jwtUtils.generateToken(userDetails);
		return new AuthenticateResponse(jwt);

	}

	@GetMapping("/signout")
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
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
