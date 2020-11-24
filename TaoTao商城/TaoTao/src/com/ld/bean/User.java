package com.ld.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.sun.istack.internal.Nullable;

@Component
public class User {
	/*
	`uid` varchar(32) NOT NULL,
    `username` varchar(20) DEFAULT NULL,
    `password` varchar(20) DEFAULT NULL,
    `name` varchar(20) DEFAULT NULL,
    `email` varchar(30) DEFAULT NULL,
    `telephone` varchar(20) DEFAULT NULL,
    `birthday` date DEFAULT NULL,
    `sex` varchar(10) DEFAULT NULL,
    `state` int(11) DEFAULT NULL,
    `code` varchar(64) DEFAULT NULL,
	 */
	@Nullable
	private String uid;
	private String username;
	private String password;
	private String name;
	private String email;
	@Nullable
	private String telephone;
	private Date birthday;
	private String sex;
	@Nullable
	private int state;//ÊÇ·ñ¼¤»î
	@Nullable
	private String code;//¼¤»îÂë
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String uid, String username, String password, String name, String email, String telephone,
			Date birthday, String sex, int state, String code) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.birthday = birthday;
		this.sex = sex;
		this.state = state;
		this.code = code;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
				+ email + ", telephone=" + telephone + ", birthday=" + birthday + ", sex=" + sex + ", state=" + state
				+ ", code=" + code + "]";
	}
	public User(String username, String password, String name, String email, Date birthday, String sex) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.birthday = birthday;
		this.sex = sex;
	}
	

}
