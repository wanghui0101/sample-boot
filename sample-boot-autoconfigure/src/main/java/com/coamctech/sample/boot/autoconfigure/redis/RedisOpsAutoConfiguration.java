package com.coamctech.sample.boot.autoconfigure.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.coamctech.sample.boot.commons.redis.RedisOps;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisOpsAutoConfiguration {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Bean
	public RedisOps redisOps() {
		return new RedisOps(stringRedisTemplate);
	}
}
