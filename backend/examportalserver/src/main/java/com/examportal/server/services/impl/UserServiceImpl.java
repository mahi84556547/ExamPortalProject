package com.examportal.server.services.impl;

import com.examportal.server.models.User;
import com.examportal.server.models.UserRole;
import com.examportal.server.repositories.RoleRepository;
import com.examportal.server.repositories.UserRepository;
import com.examportal.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findUserByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user, Set<UserRole> userRoles) throws Exception {
     User localUser=userRepository.findByUsername(user.getUsername());
     if(localUser != null){
         throw new Exception("User Already exist with username: "+user.getUsername());
     }else {
         for (UserRole role:userRoles){
             roleRepository.save(role.getRole());
         }
         user.getUserRoleSet().addAll(userRoles);
         user.setUid(UUID.randomUUID().toString());
         localUser=userRepository.save(user);
     }
        return localUser;
    }

    @Override
    public void deleteUser(String uid) throws Exception {
        User findUser=findUserByUid(uid);
        if(findUser==null){
            throw new Exception("user not found");
        }
        userRepository.delete(findUser);
    }
}
