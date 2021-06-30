package com.examportal.server.services;

import com.examportal.server.models.User;
import com.examportal.server.models.UserRole;

import java.util.Set;

public interface UserService {

    public User saveUser(User user, Set<UserRole> userRoleSet) throws Exception;
}
