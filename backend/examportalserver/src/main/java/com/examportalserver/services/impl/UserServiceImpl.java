package com.examportalserver.services.impl;

import com.examportalserver.models.User;
import com.examportalserver.models.UserRole;
import com.examportalserver.repositories.RoleRepository;
import com.examportalserver.repositories.UserRepository;
import com.examportalserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
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
         String cryptPassword=bCryptPasswordEncoder.encode(user.getPassword());
         user.setPassword(cryptPassword);
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
