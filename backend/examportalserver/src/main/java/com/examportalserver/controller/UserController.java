package com.examportalserver.controller;

import com.examportalserver.models.Role;
import com.examportalserver.models.User;
import com.examportalserver.models.UserRole;
import com.examportalserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User>getUserByUsername(@PathVariable(name = "username") String username){
        User user=userService.findUserByUsername(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception {

        Set<UserRole> userRoles=new HashSet<>();

        Role role=new Role();
        role.setName("NORMAL");

        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoles.add(userRole);

        User newUser=userService.saveUser(user,userRoles);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<User> deleteUser(@PathVariable(name = "uid") String uid)throws Exception{
        userService.deleteUser(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
