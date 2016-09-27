package com.coamctech.sample.boot.autoconfigure.fastdfs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "coamctech.fastdfs")
public class FastDfsProperties {

	private Integer connectTimeout;
	private Integer networkTimeout;
	private String charset;
	private Integer httpTrackerPort;
	private String httpAntiStealToken;
	private String httpSecretKey;
	private String trackerServer;

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getNetworkTimeout() {
		return networkTimeout;
	}

	public void setNetworkTimeout(Integer networkTimeout) {
		this.networkTimeout = networkTimeout;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Integer getHttpTrackerPort() {
		return httpTrackerPort;
	}

	public void setHttpTrackerPort(Integer httpTrackerPort) {
		this.httpTrackerPort = httpTrackerPort;
	}

	public String getHttpAntiStealToken() {
		return httpAntiStealToken;
	}

	public void setHttpAntiStealToken(String httpAntiStealToken) {
		this.httpAntiStealToken = httpAntiStealToken;
	}

	public String getHttpSecretKey() {
		return httpSecretKey;
	}

	public void setHttpSecretKey(String httpSecretKey) {
		this.httpSecretKey = httpSecretKey;
	}

	public String getTrackerServer() {
		return trackerServer;
	}

	public void setTrackerServer(String trackerServer) {
		this.trackerServer = trackerServer;
	}
	
}
