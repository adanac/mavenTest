package com.allen.common;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.allen.common.dto.UserDto;

public class ExtractTest {

	@Test
	public void test_1() {
		List<UserDto> userList = new LinkedList<UserDto>();
		for (int i = 0; i < 3; i++) {
			UserDto user = new UserDto("user_" + i, i + 10);
			userList.add(user);
		}
		processList(userList);
	}

	private void processList(List<UserDto> userList) {
		System.out.println(userList.size());
		if (userList != null && userList.size() > 0) {
			for (UserDto user : userList) {
				System.out.println("name:" + user.getUserName() + "," + "age:" + user.getAge());
			}
		}
	}

}
