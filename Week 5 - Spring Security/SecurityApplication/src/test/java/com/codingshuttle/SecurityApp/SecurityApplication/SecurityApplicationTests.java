package com.codingshuttle.SecurityApp.SecurityApplication;

import com.codingshuttle.SecurityApp.SecurityApplication.entities.User;
import com.codingshuttle.SecurityApp.SecurityApplication.services.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JWTService jwtService;
	@Test
	void contextLoads() {

		User user = new User(4L,"sahilshirodkar@gmail.com","1234","sahil");

		String token = jwtService.generateToken(user);
		System.out.println(token);

		Long id = jwtService.getUserIdFromToken("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI0IiwiZW1haWwiOiJzYWhpbHNoaXJvZGthckBnbWFpbC5jb20iLCJyb2xlcyI6WyJVU0VSIiwiQURNSU4wIl0sImlhdCI6MTc0MjE1MTUzNywiZXhwIjoxNzQyMTUxNTk3fQ.LehgDK2qnrFNfaYYbJSB_ktvCCfhE-ZEdd4D1Zo6cp2bUleoaxVVPP5bIvhlgwkZ");
		System.out.println(id);

	}

}
