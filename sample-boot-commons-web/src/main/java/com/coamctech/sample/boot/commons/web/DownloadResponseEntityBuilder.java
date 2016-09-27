package com.coamctech.sample.boot.commons.web;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.coamctech.sample.boot.commons.exception.AppException;
import com.coamctech.sample.boot.commons.utils.CommonsUtils;

public final class DownloadResponseEntityBuilder {

	private String fileName;
	private byte[] bytes;
	private ContentDispositionType contentDispositionType;
	
	public enum ContentDispositionType {
		ATTACHMENT, INLINE;
	}
	
	private DownloadResponseEntityBuilder() {
	}
	
	public static DownloadResponseEntityBuilder create() {
		return new DownloadResponseEntityBuilder();
	}
	
	public DownloadResponseEntityBuilder setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	
	public DownloadResponseEntityBuilder setBytes(byte[] bytes) {
		this.bytes = bytes;
		return this;
	}
	
	public DownloadResponseEntityBuilder setContentDispositionType(
			ContentDispositionType contentDispositionType) {
		this.contentDispositionType = contentDispositionType;
		return this;
	}
		
	public ResponseEntity<byte[]> build() {
		Assert.notNull(fileName);
		Assert.notNull(bytes);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.getByFileName(fileName));
		httpHeaders.set(HttpHeaders.CONTENT_LENGTH, bytes.length + "");
		httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, determineContentDisposition());
		return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
	}
	
	private String determineContentDisposition() {
		String encodedfileName = "nameless";
		try {
			encodedfileName = new String(fileName.getBytes(), "ISO8859-1"); // 避免乱码
		} catch (UnsupportedEncodingException e) {
			throw new AppException(e);
		}
		return determineContentDispositionType() + "; filename=\"" + encodedfileName + "\"";
	}
	
	private String determineContentDispositionType() {
		ContentDispositionType result = CommonsUtils.defaultIfNull(contentDispositionType, 
				ContentDispositionType.ATTACHMENT);
		return result.name().toLowerCase();
	}
}
