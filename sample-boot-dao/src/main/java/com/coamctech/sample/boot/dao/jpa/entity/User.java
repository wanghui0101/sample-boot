package com.coamctech.sample.boot.dao.jpa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.coamctech.sample.boot.dao.jpa.UuidEntity;

@Entity
@Table(name = "t_user")
public class User extends UuidEntity {

	private static final long serialVersionUID = -6390379656892843099L;

	private String name;
	
	private Date lastModifiedAt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	
}
