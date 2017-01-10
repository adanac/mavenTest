package com.allen.common.json.json_lib.bean;

import java.io.Serializable;

public class CompanyBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1104121355333214739L;
	private String companyName;
	private String companyCode;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}
