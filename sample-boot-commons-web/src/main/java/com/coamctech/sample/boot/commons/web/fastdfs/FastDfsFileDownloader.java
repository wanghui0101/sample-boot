package com.coamctech.sample.boot.commons.web.fastdfs;

import org.springframework.http.ResponseEntity;

import com.coamctech.sample.boot.commons.fastdfs.FastDfsFile;
import com.coamctech.sample.boot.commons.web.DownloadResponseEntityBuilder;

public final class FastDfsFileDownloader {
	
	private FastDfsFileDownloader() {
		throw new UnsupportedOperationException();
	}

	public static ResponseEntity<byte[]> download(FastDfsFile fastDfsFile) {
		return download(fastDfsFile, DownloadResponseEntityBuilder.
				ContentDispositionType.ATTACHMENT);
	}
	
	public static ResponseEntity<byte[]> download(FastDfsFile fastDfsFile,
			DownloadResponseEntityBuilder.ContentDispositionType contentDispositionType) {
		return DownloadResponseEntityBuilder.create()
				.setFileName(fastDfsFile.getFileName())
				.setBytes(fastDfsFile.getBytes())
				.setContentDispositionType(contentDispositionType)
				.build();
	}
	
}
