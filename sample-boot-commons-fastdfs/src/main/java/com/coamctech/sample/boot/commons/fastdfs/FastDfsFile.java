package com.coamctech.sample.boot.commons.fastdfs;

import java.io.Serializable;

public class FastDfsFile implements Serializable {

	private static final long serialVersionUID = 8149138737415448697L;

	private String fileName;
	private byte[] bytes;

	public FastDfsFile(String fileName, byte[] bytes) {
		this.fileName = fileName;
		this.bytes = bytes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
}
