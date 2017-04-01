package com.blue.ailk.db;

import java.util.List;

public class PDMParameter implements IParameter {
	List<String> filenames;

	public List<String> getFilenames() {
		return filenames;
	}

	public void setFilenames(List<String> filenames) {
		this.filenames = filenames;
	}
}
