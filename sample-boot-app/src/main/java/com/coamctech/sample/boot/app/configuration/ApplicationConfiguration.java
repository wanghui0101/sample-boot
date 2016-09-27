package com.coamctech.sample.boot.app.configuration;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coamctech.sample.boot.app.applicationlistener.SampleApplicationReadyListener;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public ApplicationListener<ApplicationReadyEvent> applicationReadyListener() {
		return new SampleApplicationReadyListener();
	}
	
}
