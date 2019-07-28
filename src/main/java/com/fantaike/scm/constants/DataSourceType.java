package com.fantaike.scm.constants;

public enum DataSourceType {

	// 主表
	Master("primaryDataSource"),
	// 从表
	Slave("secondaryDataSource");

	private String name;

	private DataSourceType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}