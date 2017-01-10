package com.allen.common.util;

import java.util.UUID;

public class RandomToolkit {

	public static String generateString(int count) {
		StringBuilder sb = new StringBuilder();
		UUID uuid = UUID.randomUUID();
		for (int i = 0; i < count; i++) {
			sb.append(uuid.toString().charAt(i));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(generateString(i));
		}
	}

}
