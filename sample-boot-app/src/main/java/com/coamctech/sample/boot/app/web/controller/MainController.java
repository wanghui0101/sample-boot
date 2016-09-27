package com.coamctech.sample.boot.app.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coamctech.sample.boot.app.service.UserService;
import com.coamctech.sample.boot.commons.fastdfs.FastDfsFile;
import com.coamctech.sample.boot.commons.response.Response;
import com.coamctech.sample.boot.commons.web.fastdfs.FastDfsFileDownloader;
import com.coamctech.sample.boot.dao.jpa.entity.User;
import com.coamctech.sample.boot.dao.mongo.domain.FileMetadata;

@RequestMapping("/main")
@Controller
public class MainController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public String testFreemarker(Model model) {
		Iterable<User> allUsers = userService.getAllUser();
		Iterable<FileMetadata> allFiles = userService.getAllContent();
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("allFiles", allFiles);
		return "main";
	}
	
	@GetMapping("/test-jpa")
	public @ResponseBody Response testJpa() {
		userService.testJpa();
		return Response.success();
	}
	
	@GetMapping("/test-redis")
	public @ResponseBody Response testRedis() {
		userService.testRedis();
		return Response.success();
	}
	
	@GetMapping("/test-zookeeper")
	public @ResponseBody Response testZookeeper() {
		userService.testZookeeper();
		return Response.success();
	}
	
	@PostMapping("/upload")
	public @ResponseBody Response testUploadToFastDfs(MultipartFile attachment) throws IOException {
		userService.testUploadToFastDfs(attachment);
		return Response.success();
	}
	
	@GetMapping("/download")
	public ResponseEntity<byte[]> testDownloadFromFastDfs(String fileId) {
		FastDfsFile fastDfsFile = userService.testDownloadFromFastDfs(fileId);
		return FastDfsFileDownloader.download(fastDfsFile);
	}
	
}
