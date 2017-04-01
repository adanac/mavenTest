package mavenTest;

import org.apache.commons.lang3.RandomStringUtils;

public class Test {

	public static void main(String[] args) {
		System.out.println(Math.random() * 100);
		System.out.println(Math.random() * 1000);
		System.out.println(RandomStringUtils.randomAlphanumeric(6));
	}
}
