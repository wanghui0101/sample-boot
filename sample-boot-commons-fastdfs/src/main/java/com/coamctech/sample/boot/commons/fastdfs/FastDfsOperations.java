package com.coamctech.sample.boot.commons.fastdfs;

import java.util.Map;

/**
 * FastDfs操作
 * 
 * @author wanghui
 * @lastModified 2016-3-22 09:17:08
 */
public interface FastDfsOperations {

	/**
	 * 通过FastDfsCallback接口直接获取到StorageClient对象
	 * 
	 * @param action
	 * @return
	 */
	<T> T execute(FastDfsCallback<T> action);
	
	/**
	 * 上传文件
	 * 
	 * @param bytes 文件的字节数组形式
	 * @param extension 后缀名
	 * @return 文件ID。由几部分组成：组名，虚拟磁盘路径，数据两级目录，文件名。形如：group1/M00/02/44/wKgDrE34E8wAAAAAAAAGkEIYJK42378.sh
	 */
	String upload(byte[] bytes, String extension);

	/**
	 * 上传文件
	 * 
	 * @param bytes 文件的字节数组形式
	 * @param extension 后缀名
	 * @param metaData 元数据
	 * @return 文件ID。由几部分组成：组名，虚拟磁盘路径，数据两级目录，文件名。形如：group1/M00/02/44/wKgDrE34E8wAAAAAAAAGkEIYJK42378.sh
	 */
	String upload(byte[] bytes, String extension, Map<String, String> metaData);
	
	/**
	 * 获取文件元数据
	 * @param fileId 文件ID
	 * @return 元数据
	 */
	Map<String, String> getMetaData(String fileId);

	/**
	 * 删除文件
	 * @param fileId 文件ID
	 * @return 删除结果
	 */
	Integer delete(String fileId);

	/**
	 * 下载文件
	 * @param fileId 文件ID
	 * @return 文件的字节数组形式
	 */
	byte[] download(String fileId);

	
}
