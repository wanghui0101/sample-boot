package com.coamctech.sample.boot.autoconfigure.fastdfs;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coamctech.sample.boot.commons.fastdfs.FastDfsOperations;
import com.coamctech.sample.boot.commons.fastdfs.FastDfsTemplate;
import com.coamctech.sample.boot.x.fastdfs.ClientGlobal;

@Configuration
@ConditionalOnClass(ClientGlobal.class)
@ConditionalOnProperty(prefix = "coamctech.fastdfs", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(FastDfsProperties.class)
public class FastDfsAutoConfiguration {

	private final FastDfsProperties fastDfsProperties;
	
	public FastDfsAutoConfiguration(FastDfsProperties fastDfsProperties) {
		this.fastDfsProperties = fastDfsProperties;
	}
	
	@PostConstruct
	public void initializeFastDfsClient() {
		new FastDfsInitializer(fastDfsProperties).initialize();
	}
	
	@Bean
	@ConditionalOnMissingBean(FastDfsOperations.class)
	public FastDfsOperations fastDfsTemplate() {
		return new FastDfsTemplate();
	}
	
}
