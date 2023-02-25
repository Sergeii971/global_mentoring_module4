package com.os.module4.service;

import com.os.module4.model.dto.SportDto;
import com.os.module4.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto findById(String id);

    UserDto findByEmail(String email);

    UserDto update(String userId, SportDto sportDto);

    List<UserDto> findBySportName(String sportName);

    List<UserDto> findByText(String text);
}
