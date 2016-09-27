package com.coamctech.sample.boot.commons.fastdfs;

import com.coamctech.sample.boot.x.fastdfs.StorageClient1;

public interface FastDfsCallback<T> {

	T doInFastDfs(StorageClient1 storageClient) throws Exception;
	
}
