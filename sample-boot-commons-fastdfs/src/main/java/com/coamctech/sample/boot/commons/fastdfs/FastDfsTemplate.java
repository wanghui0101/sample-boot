package com.coamctech.sample.boot.commons.fastdfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.coamctech.sample.boot.x.fastdfs.ClientGlobal;
import com.coamctech.sample.boot.x.fastdfs.StorageClient1;
import com.coamctech.sample.boot.x.fastdfs.StorageServer;
import com.coamctech.sample.boot.x.fastdfs.TrackerClient;
import com.coamctech.sample.boot.x.fastdfs.TrackerServer;
import com.coamctech.sample.boot.x.fastdfs.commons.NameValuePair;

/**
 * FastDfs模板操作类
 * 
 * @author wanghui
 * @lastModified 2016-3-21 18:42:32
 */
public class FastDfsTemplate implements FastDfsOperations {
	
	private static final Logger logger = LoggerFactory.getLogger(FastDfsTemplate.class);
	
	@Override
	public <T> T execute(FastDfsCallback<T> action) {
		return doExecute(action);
	}
	
	private <T> T doExecute(FastDfsCallback<T> action) throws FastDfsException {
		TrackerClient trackerClient = null;
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;
		
		try {
			trackerClient = new TrackerClient(ClientGlobal.getG_tracker_group());
			trackerServer = trackerClient.getConnection();
	        storageServer = trackerClient.getStoreStorage(trackerServer);
	        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
			return action.doInFastDfs(client);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new FastDfsException(e);
		} finally {
			try {
				trackerServer.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new FastDfsException(e);
			}
		}
	}
	
	@Override
	public String upload(byte[] bytes, String extension) {
		return upload(bytes, extension, new HashMap<String, String>(0));
	}
	
	@Override
	public String upload(byte[] bytes, String extension, Map<String, String> metaData) {
		Assert.notNull(metaData);
		return execute(new FastDfsCallback<String>() {

			@Override
			public String doInFastDfs(StorageClient1 storageClient) throws Exception {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(metaData.size());
				metaData.forEach((name, value) -> {
					pairs.add(new NameValuePair(name, value));
				});
				return storageClient.upload_appender_file1(bytes, extension, 
						pairs.toArray(new NameValuePair[metaData.size()]));
			}
			
		});
	}
	
	@Override
	public Map<String, String> getMetaData(String fileId) {
		return execute(new FastDfsCallback<Map<String, String>>() {

			@Override
			public Map<String, String> doInFastDfs(StorageClient1 storageClient) throws Exception {
				NameValuePair[] pairs = storageClient.get_metadata1(fileId);
				Map<String, String> metaData = new HashMap<String, String>(pairs.length);
				for (NameValuePair pair : pairs) {
					metaData.put(pair.getName(), pair.getValue());
				}
				return metaData;
			}
			
		});
	}
	
	@Override
	public Integer delete(String fileId) {
		return execute(new FastDfsCallback<Integer>() {

			@Override
			public Integer doInFastDfs(StorageClient1 storageClient) throws Exception {
				return storageClient.delete_file1(fileId);
			}
			
		});
	}
	
	@Override
	public byte[] download(String fileId) {
		return execute(new FastDfsCallback<byte[]>() {

			@Override
			public byte[] doInFastDfs(StorageClient1 storageClient) throws Exception {
				return storageClient.download_file1(fileId);
			}
			
		});
	}
	
}
