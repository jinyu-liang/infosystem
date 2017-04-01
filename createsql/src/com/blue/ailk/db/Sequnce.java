package com.blue.ailk.db;

public class Sequnce {
	String name;

	public String getPhysicalOptions() {
		return physicalOptions;
	}

	public void setPhysicalOptions(String physicalOptions) {
		this.physicalOptions = physicalOptions;
	}

	String schem;
	
	String physicalOptions;
	public String getSchem() {
		return schem;
	}

	public void setSchem(String schem) {
		this.schem = schem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
