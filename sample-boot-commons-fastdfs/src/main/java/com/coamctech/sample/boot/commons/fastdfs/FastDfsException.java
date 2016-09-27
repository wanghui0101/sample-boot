package com.coamctech.sample.boot.commons.fastdfs;

@SuppressWarnings("serial")
public class FastDfsException extends RuntimeException {

	public FastDfsException(String message) {
		super(message);
	}

	public FastDfsException(Throwable cause) {
		super(cause);
	}

	public FastDfsException(String message, Throwable cause) {
		super(message, cause);
	}
}
