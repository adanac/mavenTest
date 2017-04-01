package com.allen.annotation;

import com.allen.annotation.FruitColor.Color;

/**
 * 测试自定义注解
 * 
 * @author allen
 *
 */
public class Apple {

	@FruitName("apple")
	private String fruitName;

	@FruitColor(fruitColor = Color.RRR)
	private String fruitColor;

	public String getFruitName() {
		return fruitName;
	}

	public String getFruitColor() {
		return fruitColor;
	}

	public void setFruitColor(String fruitColor) {
		this.fruitColor = fruitColor;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public void displayName() {
		System.out.println("the fruit's name is " + fruitName);
		System.out.println("the fruit's color is " + fruitColor);
	}
}
