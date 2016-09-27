package com.coamctech.sample.boot.dao.mongo;

import org.springframework.data.annotation.Id;

public class AbstractDocument {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
