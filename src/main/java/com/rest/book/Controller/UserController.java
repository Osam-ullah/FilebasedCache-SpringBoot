package com.rest.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.book.Dto.CacheResponse;
import com.rest.book.Dto.UserDto;
import com.rest.book.Service.UserService;
import com.rest.book.Service.Impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            List<UserDto> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-by-age")
    public ResponseEntity<List<UserDto>> getUsersByAge(@RequestParam int age) {
        try {
            List<UserDto> users = userService.getByAge(age);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<List<UserDto>> getUsersByName(@RequestParam String name) {
        try {
            List<UserDto> users = userService.getUsersByName(name);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-by-name-age")
    public ResponseEntity<List<UserDto>> getUsersByNameAndAge(@RequestParam String name, @RequestParam int age) {
        try {
            List<UserDto> users = userService.getUsersByNameAndAge(name, age);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/order-by-age")
    public ResponseEntity<List<UserDto>> getUserOrderByAge() {
        try {
            List<UserDto> users = userService.getUserOrderByAge();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        try {
            UserDto createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/get-from-cache")
    public ResponseEntity<CacheResponse> getFromCache(@RequestParam String key) {
        try {
            System.out.println("api called ");
            CacheResponse cacheResponse = userServiceImpl.gettingByCache(key);
            return new ResponseEntity<>(cacheResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
