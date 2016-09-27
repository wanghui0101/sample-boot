package com.coamctech.sample.boot.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.coamctech.sample.boot.commons.fastdfs.FastDfsFile;
import com.coamctech.sample.boot.dao.jpa.entity.User;
import com.coamctech.sample.boot.dao.mongo.domain.FileMetadata;

public interface UserService {

	void testJpa();
	
	void testRedis();
	
	void testZookeeper();
	
	void testUploadToFastDfs(MultipartFile attachment) throws IOException;

	Iterable<User> getAllUser();

	Iterable<FileMetadata> getAllContent();

	FastDfsFile testDownloadFromFastDfs(String fileId);

}
