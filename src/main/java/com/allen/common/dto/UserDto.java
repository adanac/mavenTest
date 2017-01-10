package com.allen.common.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private Integer age;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public UserDto(String userName, Integer age) {
		super();
		this.userName = userName;
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", age=" + age + "]";
	}

}
