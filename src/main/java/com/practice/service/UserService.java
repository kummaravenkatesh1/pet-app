package com.practice.service;

import com.practice.dto.UserDto;
import com.practice.response.Response;

public interface UserService {
Response checkLogin(UserDto userDto);

}
