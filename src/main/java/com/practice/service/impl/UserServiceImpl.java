package com.practice.service.impl;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.practice.dto.UserDto;
import com.practice.repository.UserRepository;
import com.practice.response.Response;
import com.practice.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Response checkLogin(UserDto userDto) {
		Optional<User> user = Optional.empty();
		User user1 = null;
		String message = null;
		if (user.isPresent()) {
			user1 = user.get();
			if (user1.getPassword().equals(userDto.getPassword())) {
				message = "Customer Login Successfull";
				return new Response(HttpStatus.OK,message);
			} else {
				message = "Incorrect Password";
				return new Response(HttpStatus.BAD_REQUEST,message);
			}

		} else {
			message = "Please Register First";
			return new Response(HttpStatus.BAD_REQUEST,message);
		}

	}

}