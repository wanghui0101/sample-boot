package com.coamctech.sample.boot.app.applicationlistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.coamctech.sample.boot.commons.listener.ApplicationReadyListener;

public class SampleApplicationReadyListener extends ApplicationReadyListener {

	private static final Logger logger = LoggerFactory.getLogger(SampleApplicationReadyListener.class);
	
	@Override
	protected void onApplicationReady(ConfigurableApplicationContext applicationContext) {
		logger.info("Spring容器初始化完毕 - {}", applicationContext.getClass().getName());
	}

}
