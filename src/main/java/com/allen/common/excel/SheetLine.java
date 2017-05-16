package com.allen.common.excel;

/**
 * excel表的sheet行解析器接口
 * @author xuneng
 *
 */
public interface SheetLine {

	/**
	 * 抽取cell中的内容
	 */
	void parse();
	
	/**
	 * 校验excelsheet行中的内容
	 * @return
	 */
	boolean checkLine();
	
}
