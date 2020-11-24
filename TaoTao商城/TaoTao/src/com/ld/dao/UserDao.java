package com.ld.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.ld.bean.User;

public interface UserDao {
	@Autowired
	int addUser(User user);
	void active(@Param("a") int a,@Param("active") String active);
	User findName(@Param("username")String username,@Param("password")String password);

}
