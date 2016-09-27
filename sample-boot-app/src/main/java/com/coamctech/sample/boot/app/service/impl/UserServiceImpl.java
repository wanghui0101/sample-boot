package com.coamctech.sample.boot.app.service.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.coamctech.sample.boot.app.service.UserService;
import com.coamctech.sample.boot.commons.fastdfs.FastDfsFile;
import com.coamctech.sample.boot.commons.fastdfs.FastDfsOperations;
import com.coamctech.sample.boot.commons.redis.RedisOps;
import com.coamctech.sample.boot.commons.zookeeper.curator.CuratorOperations;
import com.coamctech.sample.boot.dao.jpa.entity.User;
import com.coamctech.sample.boot.dao.jpa.repository.UserRepository;
import com.coamctech.sample.boot.dao.mongo.domain.FileMetadata;
import com.coamctech.sample.boot.dao.mongo.repository.FileMetadataRepository;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RedisOps redisOps;
	
	@Autowired
	private CuratorOperations curatorOperations;
	
	@Autowired
	private FastDfsOperations fastDfsOperations;
	
	@Autowired
	private FileMetadataRepository fileMetadataRepository;
	
	@Transactional
	@Override
	public void testJpa() {
		User user = new User();
		user.setName("wh");
		user.setLastModifiedAt(new Date());
		userRepository.save(user);
	}
	
	@Override
	public Iterable<User> getAllUser() {
		return userRepository.findAll(new Sort("lastModifiedAt"));
	}

	@Override
	public void testRedis() {
		String key = "sample-boot";
		ValueOperations<String, String> valueOps = redisOps.getValueOps();
		valueOps.set(key, "sample-boot-redis, ok!");
		logger.info(valueOps.get(key));
		redisOps.deleteKey(key);
	}

	@Override
	public void testZookeeper() {
		String path = "/sample-boot/tmp";
		curatorOperations.create(path, new String("sample-boot-zookeeper, ok!")
				.getBytes());
		String data = new String(curatorOperations.getData(path));
		logger.info(data);
		curatorOperations.delete(path);
	}
	
	@Override
	public void testUploadToFastDfs(MultipartFile attachment) throws IOException {
		byte[] bytes = attachment.getBytes();
		String originalFilename = attachment.getOriginalFilename();
		String fileId = fastDfsOperations.upload(bytes, StringUtils
				.substringAfterLast(originalFilename, "."));
		logger.info(fileId);
		FileMetadata fileMetadata = new FileMetadata(originalFilename, fileId);
		fileMetadataRepository.save(fileMetadata);
	}
	
	@Override
	public FastDfsFile testDownloadFromFastDfs(String fileId) {
		FileMetadata fileMetadata = fileMetadataRepository.findByFileId(fileId);
		byte[] bytes = fastDfsOperations.download(fileId);
		return new FastDfsFile(fileMetadata.getFileName(), bytes);
	}
	
	@Override
	public Iterable<FileMetadata> getAllContent() {
		return fileMetadataRepository.findAll();
	}
	
}
