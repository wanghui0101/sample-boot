package com.coamctech.sample.boot.dao.mongo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.sample.boot.dao.mongo.domain.FileMetadata;

public interface FileMetadataRepository extends PagingAndSortingRepository<FileMetadata, String> {

	FileMetadata findByFileId(String fileId);
}
