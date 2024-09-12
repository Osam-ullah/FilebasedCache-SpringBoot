package com.rest.book.Service;

import java.util.List;

import com.rest.book.Dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);

    List<UserDto> getAllUsers();

    List<UserDto> getByAge(int age);

    List<UserDto> getUsersByName(String name);

    List<UserDto> getUsersByNameAndAge(String name, int age);

    List<UserDto> getUserOrderByAge();
}
