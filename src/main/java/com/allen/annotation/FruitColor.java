package com.allen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 水果颜色
 * 
 * @author allen
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

	/**
	 * 颜色枚举
	 * 
	 * @author allen
	 *
	 */
	public enum Color {
		BLUE, RRR, YELLOW
	}

	/**
	 * 颜色属性
	 * 
	 * @return
	 */
	Color fruitColor() default Color.RRR;
}
