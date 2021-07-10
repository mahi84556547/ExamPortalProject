package com.examportalserver.services;

import com.examportalserver.models.User;
import com.examportalserver.models.UserRole;

import java.util.Set;

public interface UserService {

    public User findUserByUid(String uid);
    public User findUserByUsername(String username);
    public User saveUser(User user, Set<UserRole> userRoleSet) throws Exception;
    public void deleteUser(String uid) throws Exception;
}
