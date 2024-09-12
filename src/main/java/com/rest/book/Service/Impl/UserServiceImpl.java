package com.rest.book.Service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.book.Dto.CacheResponse;
import com.rest.book.Dto.UserDto;
import com.rest.book.Model.UserModel;
import com.rest.book.Repository.UserRepository;
import com.rest.book.Service.UserService;
import com.rest.book.Utils.CacheUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheUtil cacheUtil;

    @Override
    public List<UserDto> getAllUsers() {
        try {
            List<UserModel> list = this.userRepository.getAllUser();
            return list.stream().map(this::mapToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users", e);
        }
    }

    @Override
    public List<UserDto> getByAge(int age) {
        try {
            List<UserModel> list = this.userRepository.getByAge(age);
            return list.stream().map(this::mapToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users by age", e);
        }
    }

    @Override
    public List<UserDto> getUsersByName(String name) {
        try {
            List<UserModel> list = this.userRepository.getUsersByName(name);
            return list.stream().map(this::mapToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users by name", e);
        }
    }

    @Override
    public List<UserDto> getUsersByNameAndAge(String name, int age) {
        try {
            List<UserModel> list = this.userRepository.getByNameAndAge(name, age);
            return list.stream().map(this::mapToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users by name and age", e);
        }
    }

    @Override
    public List<UserDto> getUserOrderByAge() {
        try {
            List<UserModel> list = this.userRepository.getUsersOrderedByAge();
            return list.stream().map(this::mapToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users ordered by age", e);
        }
    }

    public UserModel mapToModel(UserDto user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setAge(user.getAge());
        userModel.setName(user.getName());
        userModel.setQualification(user.getQualification());

        return userModel;
    }

    public UserDto mapToDto(UserModel user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAge(user.getAge());
        userDto.setName(user.getName());
        userDto.setQualification(user.getQualification());

        return userDto;
    }

    @Override
    public UserDto createUser(UserDto user) {
        try {

            UserModel userModel = mapToModel(user);
            UserModel savedUser = this.userRepository.save(userModel);
            cacheUtil.put(savedUser.getName(), savedUser);

            return mapToDto(savedUser);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user", e);
        }
    }

    public CacheResponse gettingByCache(String key) throws IOException {
        CacheResponse cacheResponse = new CacheResponse();

        UserModel userModel = userRepository.getUserBYName(key);
        Object cached = cacheUtil.get(userModel.getName());
        cacheResponse = CacheResponse.builder().data(cached).build();

        return cacheResponse;

    }

}
