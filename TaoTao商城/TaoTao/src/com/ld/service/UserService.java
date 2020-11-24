package com.ld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld.bean.User;
import com.ld.dao.UserDao;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	public int addUser(User user) {
		int a = userDao.addUser(user);
		return a;
	}
	public void active(int a,String active) {
		userDao.active(a,active);
	}
	public User findName(String username,String password) {
		User user = userDao.findName(username,password);
		return user;
	}

}
