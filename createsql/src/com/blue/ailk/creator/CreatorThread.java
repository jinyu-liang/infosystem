package com.blue.ailk.creator;

import java.util.List;

public class CreatorThread implements Runnable {
	public CreatorThread(List<AbstractBase> creators) {
		this.creators = creators;
	}

	@Override
	public void run() {
		for (AbstractBase creator : this.creators) {
			creator.create();
		}
	}

	List<AbstractBase> creators;

	public List<AbstractBase> getCreators() {
		return creators;
	}

	public void setCreators(List<AbstractBase> creators) {
		this.creators = creators;
	}
}
