package com.coamctech.sample.boot.commons.redis;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

public class RedisOps {

    private final StringRedisTemplate stringRedisTemplate;

    private HashOperations<String, String, String> hashOps;

    private HyperLogLogOperations<String, String> hyperLogLogOps;

    private ListOperations<String, String> listOps;

    private SetOperations<String, String> setOps;

    private ValueOperations<String, String> valueOps;

    private ZSetOperations<String, String> zsetOps;
    
    public RedisOps(StringRedisTemplate stringRedisTemplate) {
    	this.stringRedisTemplate = stringRedisTemplate;
    }
    
    @PostConstruct
    public void setUp() {
        hashOps = stringRedisTemplate.opsForHash();
        hyperLogLogOps = stringRedisTemplate.opsForHyperLogLog();
        listOps = stringRedisTemplate.opsForList();
        setOps = stringRedisTemplate.opsForSet();
        valueOps = stringRedisTemplate.opsForValue();
        zsetOps = stringRedisTemplate.opsForZSet();
    }
    
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }
    
    public void deleteKey(String... keys) {
        stringRedisTemplate.delete(Arrays.asList(keys));
    }
    
    public void expire(String key, Long timeout, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, timeout, timeUnit);
    }
    
    public Collection<String> getKeys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }
    
    // ------------ getters -------------
	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	public HashOperations<String, String, String> getHashOps() {
		return hashOps;
	}

	public HyperLogLogOperations<String, String> getHyperLogLogOps() {
		return hyperLogLogOps;
	}

	public ListOperations<String, String> getListOps() {
		return listOps;
	}

	public SetOperations<String, String> getSetOps() {
		return setOps;
	}

	public ValueOperations<String, String> getValueOps() {
		return valueOps;
	}

	public ZSetOperations<String, String> getZsetOps() {
		return zsetOps;
	}

}
