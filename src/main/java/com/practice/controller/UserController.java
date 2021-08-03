package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.dto.UserDto;
import com.practice.response.Response;
import com.practice.service.UserService;

@RestController
@RequestMapping("/user")

public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<Response> userLogin(@RequestBody UserDto userDto) {

		return ResponseEntity.ok(userService.checkLogin(userDto));

	}

}