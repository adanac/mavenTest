package com.allen.common.excel;

import com.allen.common.constant.StringConstants;
import com.allen.common.util.NormalUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * excel表的sheet行解析器抽象类
 * @author xuneng
 *
 */
public abstract class AbstractSheetLine implements SheetLine{
	
	/**
	 * 检查excel填写的内容，如下的格式内容：参与实体（8002；9001；9002）
	 * @param param
	 * @return [flag,合法的编码集合]
	 */
	List<Object> checkParam(String param){
		List<Object> ret = new ArrayList<Object>();
		List<String> codes = new ArrayList<String>();
		String[] array = null;
		boolean flag = true;
		if (NormalUtil.isNullOrEmpty(param)) {
			ret.add(false);
			ret.add(codes);
			return ret;
		}
		if (param.indexOf(StringConstants.FEN_CN_SEPARATOR) != -1) {
			array = param.split(StringConstants.FEN_CN_SEPARATOR);
		} else {
			array = new String[]{param};
		}
		for(String g : array){
			try {
				Long.parseLong(g);
				codes.add(g);
			} catch (Exception e) {
				flag = false;
			}
		}
		ret.add(flag);
		ret.add(codes);
		return ret;
	}
}