package com.coamctech.sample.boot.dao.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.coamctech.sample.boot.dao.mongo.AbstractDocument;

@Document
public class FileMetadata extends AbstractDocument {

	private String fileName;
	private String fileId;

	public FileMetadata(String fileName, String fileId) {
		this.fileName = fileName;
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}
