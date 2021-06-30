package com.examportal.server.controller;

import com.examportal.server.models.Role;
import com.examportal.server.models.User;
import com.examportal.server.models.UserRole;
import com.examportal.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception {

        Set<UserRole> userRoles=new HashSet<>();

        Role role=new Role();
        role.setId(1L);
        role.setName("NORMAL");

        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoles.add(userRole);

        User newUser=userService.saveUser(user,userRoles);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
