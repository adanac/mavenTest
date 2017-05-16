package com.allen.common.excel;

import com.allen.common.constant.StringConstants;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;


/**
 * excel的补偿信息sheet中的行
 * @author xuneng
 *
 */
public class SinglePmCompSheetLine extends AbstractSheetLine{
	
	private List<Object> line;
	
	private Vector<CompensateInfoPoExt> compensateInfoPoExts = null;
	
	private List<String> compLineList = new LinkedList<String>();
	
	private List<String> ruleList = null;
	
	private List<String> compList = null;
	
	private boolean flag = false;
	
	private String skuIdStr = null;
	
	private String entityStr = null;
	
	private String channelStr = null;
	
	private String giftStr = null;
	
	private String supplierIdStr = null;

	private String specPriceStr = null;

	private String agioRateStr = null;
	
	public void parse(){
		
		this.skuIdStr = String.valueOf(line.get(0));
		
		this.entityStr = String.valueOf(line.get(1));
		
		this.channelStr = String.valueOf(line.get(2));
		
		this.giftStr = String.valueOf(line.get(3));
		
		this.supplierIdStr = String.valueOf(line.get(4));
		
		this.specPriceStr = String.valueOf(line.get(5));

		this.agioRateStr = String.valueOf(line.get(6));
	}

	
	public boolean checkLine(){
		boolean flag = true;
		StringBuffer sb = new StringBuffer();
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
		List<String> storeList = (List<String>)checkStores.get(1);//合法的编码
		for(String store : storeList){
			compLineList.add(store + "_" + skuIdStr);
			compList.add(store + "_" + skuIdStr);
		}
		
		// 重复的参与实体
		List<String> repeatList = CompensateFilter(compList);
		if (repeatList != null && repeatList.size() > 0) {
			sb.append("规则中存在重复的参与实体【" + repeatList + "】");
			flag = false;
		}

		// 不存在的参与实体
		List<String> noneList = CompensateFilter(compLineList, ruleList);
		if (noneList != null && noneList.size() > 0) {
			sb.append("规则中不存在的参与实体【" + noneList + "】;");
			flag = false;
		}
		
		//赠品
		List<Object> checkGift = checkParam(giftStr);
		if(!(Boolean)checkGift.get(0)){
			sb.append("赠品编码不能为空;赠品编码【" + giftStr + "】不正确;");
			flag = false;
		}
		
		//供应商
		List<Object> checkSupplier = checkParam(supplierIdStr);
		if(!(Boolean)checkSupplier.get(0)){
			sb.append("供应商编码不能为空;供应商编码【" + supplierIdStr + "】不正确;");
			flag = false;
		}
		

		// 供应商承担比例
		msg = "供应商承担比例【" + agioRateStr + "】不正确;";
		try {
			if(Long.parseLong(agioRateStr) < 0){
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

	public boolean isFlag() {
		return flag;
	}

	public void setLine(List<Object> line) {
		this.line = line;
	}

	public void setRuleList(List<String> ruleList) {
		this.ruleList = ruleList;
	}

	public void setCompList(List<String> compList) {
		this.compList = compList;
	}

	private List<String> CompensateFilter(List<String> compList) {
		List<String> repeat = new LinkedList<String>();
		if (compList != null && compList.size() > 0) {
			for (int i = 0; i < compList.size() - 1; i++) {
				for (int j = compList.size() - 1; j > i; j--) {
					if (compList.get(j).equals(compList.get(i))) {
						repeat.add(compList.get(j));
					}
				}
			}
		}
		return repeat;
	}

	private List<String> CompensateFilter(List<String> compList, List<String> ruleList) {
		List<String> none = new LinkedList<String>();
		if (ruleList != null && ruleList.size() > 0) {
			for (String comp : compList) {
				if (!ruleList.contains(comp)) {
					none.add(comp);
				}
			}
		}
		return none;
	}

	public String getSkuIdStr() {
		return skuIdStr;
	}

	public String getEntityStr() {
		return entityStr;
	}

	public String getChannelStr() {
		return channelStr;
	}

	public String getGiftStr() {
		return giftStr;
	}

	public String getSupplierIdStr() {
		return supplierIdStr;
	}

	public String getSpecPriceStr() {
		return specPriceStr;
	}

	public String getAgioRateStr() {
		return agioRateStr;
	}

	public Vector<CompensateInfoPoExt> getCompensateInfoPoExts() {
		return compensateInfoPoExts;
	}

	
}
