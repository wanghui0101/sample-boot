package com.coamctech.sample.boot.commons.utils;

import org.apache.commons.lang3.StringUtils;

public abstract class CommonsUtils {

	public static String extensionOf(String fileName) {
		return StringUtils.substringAfterLast(fileName, ".").toLowerCase();
	}
	
	public static <T> T defaultIfNull(T sourceObject, T defaultObject) {
		return sourceObject != null ? sourceObject : defaultObject;
	}
}
