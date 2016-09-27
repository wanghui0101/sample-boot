package com.coamctech.sample.boot.commons.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		onApplicationReady(event.getApplicationContext());
	}

	protected abstract void onApplicationReady(ConfigurableApplicationContext applicationContext);

}
