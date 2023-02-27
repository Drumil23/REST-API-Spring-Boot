package com.javadrumil.demo.service;

import com.javadrumil.demo.entity.User;
import com.javadrumil.demo.entity.UserModel;

public interface UserService {
	User createUser(UserModel user);
	User readUser();
	User updateUser(UserModel user);
	void deleteUser();
	User getLoggedInUser();

}
