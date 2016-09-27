package com.coamctech.sample.boot.commons.zookeeper.curator.aop;

public class AcquireLockTimeoutException extends RuntimeException {

	private static final long serialVersionUID = 4779930855308585940L;
	
	public AcquireLockTimeoutException(String message) {
		super(message);
	}

	public AcquireLockTimeoutException(Throwable cause) {
		super(cause);
	}

	public AcquireLockTimeoutException(String message, Throwable cause) {
		super(message, cause);
	}

}
