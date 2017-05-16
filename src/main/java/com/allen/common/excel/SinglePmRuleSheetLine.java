package com.allen.common.excel;

import com.allen.common.constant.StringConstants;
import com.allen.common.util.NormalUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * excel的规则信息sheet中的行
 * @author xuneng
 *
 */
public class SinglePmRuleSheetLine extends AbstractSheetLine{
	
	private List<Object> line;
	

	private boolean flag = false;
	
	private List<String> ruleList;
	
	private String entityStr = null;
	private String channelIncludeStr = null;
	private String skuIdStr = null;
	private String ruleTypeStr = null;
	private String userStr = null;
	private String itemPriceModeStr = null;
	private String scoreStr = null;
	private String giftGrantTypeStr = null;
	private String giftGrantWayStr = null;
	private String skuNumStr = null;
	private String compTypeStr = null;
	private String giftStr = null;
	private String startTimeStr = null;
	private String endTimeStr = null;
	private String maxDiscountAmtStr = null;
	private String applyTimesPerUserStr = null;
	private String applyTimesPerOrderStr = null;
	private String defaultSpecStr = null;
	private String defaultRadioStr = null;
	
	public SinglePmRuleSheetLine() {
		super();
	}
	
	/**
	 * 抽取cell中的内容
	 */
	public void parse(){
		this.entityStr = String.valueOf(line.get(0));
		this.channelIncludeStr = String.valueOf(line.get(1));
		this.skuIdStr = String.valueOf(line.get(2));
		this.ruleTypeStr = String.valueOf(line.get(3));
		this.userStr = String.valueOf(line.get(4));
		this.itemPriceModeStr = String.valueOf(line.get(5));
		this.scoreStr = String.valueOf(line.get(6));
		this.giftGrantTypeStr = String.valueOf(line.get(7));
		this.giftGrantWayStr = String.valueOf(line.get(8));
		this.skuNumStr = String.valueOf(line.get(9));
		this.compTypeStr = String.valueOf(line.get(10));
		this.giftStr = String.valueOf(line.get(11));
		this.startTimeStr = String.valueOf(line.get(12));
		this.endTimeStr = String.valueOf(line.get(13));
		this.maxDiscountAmtStr = String.valueOf(line.get(14));
		this.applyTimesPerUserStr = String.valueOf(line.get(15));
		this.applyTimesPerOrderStr = String.valueOf(line.get(16));
		this.defaultSpecStr = String.valueOf(line.get(17));
		this.defaultRadioStr = String.valueOf(line.get(18));
	}
	

	
	public boolean checkLine(){
		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		String msg = "商品ID【" + skuIdStr + "】不正确;";
		// 商品ID
		try {
			Long.parseLong(skuIdStr);
		} catch (Exception e) {
			sb.append(msg);
			flag = false;
		}

		// 参与实体
		List<Object> checkStores = checkParam(entityStr);
		if(!(Boolean)checkStores.get(0)){
			sb.append("门店编码【" + entityStr + "】不正确;");
			flag = false;
		}
		
		@SuppressWarnings("unchecked")
		List<String> storeList = (List<String>)checkStores.get(1);//合法的门店编码集合
		for(String store : storeList){
			ruleList.add(store + "_" + skuIdStr);
		}


		

		//活动开始时间
		msg = "活动开始时间【" + startTimeStr + "】不正确;";
		long startTime = this.getStartTime();
		if(startTime == -1){
			sb.append(msg);
			flag = false;
		}
		
		//活动结束时间
		msg = "活动开始时间【" + endTimeStr + "】不正确;";
		long endTime = this.getEndTime();
		if(endTime == -1){
			sb.append(msg);
			flag = false;
		}
		
		if(endTime < startTime){
			msg = "活动开始时间不能晚于活动结束时间;";
			sb.append(msg);
			flag = false;
		}
		long now = new Date().getTime() / 1000;
		if(now > startTime){
			msg = "活动开始时间不能早于当前时间;";
			sb.append(msg);
			flag = false;
		}
		
		//规则使用次数
		msg = "规则使用次数限制【" + maxDiscountAmtStr + "】不正确;";
		try {
			if(Long.parseLong(maxDiscountAmtStr) < 0){
				sb.append(msg);
				flag = false;
			}
		} catch (Exception e) {
			sb.append(msg);
			flag = false;
		}

		// 用户参与购买次数限制
		msg = "用户参与购买次数限制【" + applyTimesPerUserStr + "】不正确;";
		try {
			if(Long.parseLong(applyTimesPerUserStr) < 0){
				sb.append(msg);
				flag = false;
			}
		} catch (Exception e) {
			sb.append(msg);
			flag = false;
		}

		// 用户当日参与次数限制
		msg = "用户当日参与次数限制【" + applyTimesPerOrderStr + "】不正确;";
		try {
			if(Long.parseLong(applyTimesPerOrderStr) < 0){
				sb.append(msg);
				flag = false;
			}
		} catch (Exception e) {
			sb.append(msg);
			flag = false;
		}



		// 默认供应商承担比例
		msg = "默认供应商承担比例【" + defaultRadioStr + "】不正确;";
		try {
			if(Long.parseLong(defaultRadioStr) < 0){
				sb.append(msg);
				flag = false;
			}
		} catch (Exception e) {
			sb.append(msg);
			flag = false;
		}

		if (flag) {
			line.set(line.size() - 1, StringConstants.SUCCESS);
		} else {
			line.set(line.size() - 1, sb.toString());
		}
		this.flag = flag;
		return flag;
	}
	

	
	/**
	 * 主商品ID
	 * @return
	 */
	private long getSkuId(){
		
		return Long.parseLong(skuIdStr);
	}
	


	/**
	 * 活动开始时间
	 * @return
	 */
	private long getStartTime(){
		
		try {
			return (NormalUtil.GetLongByDate(startTimeStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 活动结束时间
	 * @return
	 */
	private long getEndTime(){
		
		try {
			return (NormalUtil.GetLongByDate(endTimeStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private long getMaxDiscountAmt(){
		
		return (Long.parseLong(maxDiscountAmtStr));
	}
	
	private long getApplyTimesPerUser(){
		
		return (Long.parseLong(applyTimesPerUserStr));
	}
	
	private long getApplyTimesPerOrder(){
		
		return (Long.parseLong(applyTimesPerOrderStr));
	}
	
	/**
	 * 规则名称
	 * @return
	 */
	private String getRuleName(){
		return ruleTypeStr;
	}
	
	public void setLine(List<Object> line) {
		this.line = line;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setRuleList(List<String> ruleList) {
		this.ruleList = ruleList;
	}

	public String getEntityStr() {
		return entityStr;
	}

	public String getChannelIncludeStr() {
		return channelIncludeStr;
	}

	public String getSkuIdStr() {
		return skuIdStr;
	}

	public String getRuleTypeStr() {
		return ruleTypeStr;
	}

	public String getUserStr() {
		return userStr;
	}

	public String getItemPriceModeStr() {
		return itemPriceModeStr;
	}

	public String getScoreStr() {
		return scoreStr;
	}

	public String getGiftGrantTypeStr() {
		return giftGrantTypeStr;
	}

	public String getGiftGrantWayStr() {
		return giftGrantWayStr;
	}

	public String getSkuNumStr() {
		return skuNumStr;
	}

	public String getCompTypeStr() {
		return compTypeStr;
	}

	public String getGiftStr() {
		return giftStr;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public String getMaxDiscountAmtStr() {
		return maxDiscountAmtStr;
	}

	public String getApplyTimesPerUserStr() {
		return applyTimesPerUserStr;
	}

	public String getApplyTimesPerOrderStr() {
		return applyTimesPerOrderStr;
	}

	public String getDefaultSpecStr() {
		return defaultSpecStr;
	}

	public String getDefaultRadioStr() {
		return defaultRadioStr;
	}
	
}
