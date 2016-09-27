package com.coamctech.sample.boot.autoconfigure.fastdfs;

import java.net.InetSocketAddress;

import org.springframework.util.StringUtils;

import com.coamctech.sample.boot.commons.fastdfs.FastDfsException;
import com.coamctech.sample.boot.x.fastdfs.ClientGlobal;
import com.coamctech.sample.boot.x.fastdfs.TrackerGroup;

public class FastDfsInitializer {

	private final FastDfsProperties fastDfsProperties;
	
	public FastDfsInitializer(FastDfsProperties fastDfsProperties) {
		this.fastDfsProperties = fastDfsProperties;
	}
	
	/**
	 * @see com.coamctech.sample.boot.x.fastdfs.ClientGlobal.init(IniFileReader iniReader)
	 */
	public void initialize() {
		String[] szTrackerServers;
		String[] parts;

		ClientGlobal.g_connect_timeout = getIntValue(fastDfsProperties.getConnectTimeout(),
				ClientGlobal.DEFAULT_CONNECT_TIMEOUT);
		if (ClientGlobal.g_connect_timeout < 0) {
			ClientGlobal.g_connect_timeout = ClientGlobal.DEFAULT_CONNECT_TIMEOUT;
		}
		ClientGlobal.g_connect_timeout *= 1000; // millisecond

		ClientGlobal.g_network_timeout = getIntValue(fastDfsProperties.getNetworkTimeout(), 
				ClientGlobal.DEFAULT_NETWORK_TIMEOUT);
		if (ClientGlobal.g_network_timeout < 0) {
			ClientGlobal.g_network_timeout = ClientGlobal.DEFAULT_NETWORK_TIMEOUT;
		}
		ClientGlobal.g_network_timeout *= 1000; // millisecond

		ClientGlobal.g_charset = getStringValue(fastDfsProperties.getCharset());
		if (ClientGlobal.g_charset == null || ClientGlobal.g_charset.length() == 0) {
			ClientGlobal.g_charset = "ISO8859-1";
		}

		szTrackerServers = StringUtils.commaDelimitedListToStringArray(fastDfsProperties.getTrackerServer());
		if (szTrackerServers == null || szTrackerServers.length == 0) {
			throw new FastDfsException("trackServer不能为空");
		}

		InetSocketAddress[] tracker_servers = new InetSocketAddress[szTrackerServers.length];
		for (int i = 0; i < szTrackerServers.length; i++) {
			parts = szTrackerServers[i].split("\\:", 2);
			if (parts.length != 2) {
				throw new FastDfsException(
						"the value of item \"tracker_server\" is invalid, the correct format is host:port");
			}

			tracker_servers[i] = new InetSocketAddress(parts[0].trim(),
					Integer.parseInt(parts[1].trim()));
		}
		ClientGlobal.g_tracker_group = new TrackerGroup(tracker_servers);

		ClientGlobal.g_tracker_http_port = getIntValue(fastDfsProperties.getHttpTrackerPort(), 80);
		ClientGlobal.g_anti_steal_token = getBooleanValue(fastDfsProperties.getHttpAntiStealToken());
		if (ClientGlobal.g_anti_steal_token) {
			ClientGlobal.g_secret_key = getStringValue(fastDfsProperties.getHttpSecretKey());
		}
	}
	
	private int getIntValue(Integer i, int defaultIfNull) {
		return i != null ? i : defaultIfNull;
	}
	
	private String getStringValue(String str) {
		return getStringValue(str, "");
	}
	
	private String getStringValue(String str, String defaultIfNull) {
		return str != null ? str : defaultIfNull;
	}
	
	private boolean getBooleanValue(String bool) {
		return Boolean.valueOf(bool);
	}
}
