package com.allen.common.json.json_lib.bean;

import java.io.Serializable;

public class JobBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8504098640047782206L;
	private String jobName;
	private String jobSalary;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobSalary() {
		return jobSalary;
	}

	public void setJobSalary(String jobSalary) {
		this.jobSalary = jobSalary;
	}

}
