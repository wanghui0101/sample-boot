package com.coamctech.sample.boot.autoconfigure.shiro;

import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({SecurityManager.class, ShiroFilterFactoryBean.class})
@ConditionalOnProperty(prefix = "coamctech.shiro", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroAutoConfiguration {
	
	private final ShiroProperties shiroProperties;
	
	public ShiroAutoConfiguration(ShiroProperties shiroProperties) {
		this.shiroProperties = shiroProperties;
	}
	
	@Autowired
	@Qualifier("shiroRealms")
	private Collection<Realm> realms;
	
	@Autowired
	@Qualifier("shiroFilters")
	private Map<String, Filter> filters;
	
	@PostConstruct
	public void init() {
		SecurityUtils.setSecurityManager(securityManager());
	}
    
    @Bean
    public SecurityManager securityManager() {
    	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    	SubjectDAO subjectDAO = securityManager.getSubjectDAO();
    	if (subjectDAO instanceof DefaultSubjectDAO) {
    		SessionStorageEvaluator sessionStorageEvaluator = 
    				((DefaultSubjectDAO) subjectDAO).getSessionStorageEvaluator();
    		if (sessionStorageEvaluator instanceof DefaultSessionStorageEvaluator) {
    			((DefaultSessionStorageEvaluator) sessionStorageEvaluator)
    					.setSessionStorageEnabled(false);
    		}
    	}
    	securityManager.setRealms(realms);
    	securityManager.setSessionManager(sessionManager());
    	return securityManager;
    }
    
    @Bean
    public SessionManager sessionManager() {
    	DefaultSessionManager sessionManager = new DefaultSessionManager();
    	sessionManager.setSessionValidationSchedulerEnabled(false);
    	return sessionManager;
    }
    
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
    	ShiroFilterFactoryBean sffb = new ShiroFilterFactoryBean();
    	sffb.setLoginUrl(shiroProperties.getLoginUrl());
    	sffb.setSuccessUrl(shiroProperties.getSuccessUrl());
    	sffb.setFilters(filters);
    	sffb.setFilterChainDefinitionMap(shiroProperties.getFilterChainDefinitionMap());
    	return sffb;
    }
    
    /*p:securityManager-ref="securityManager"
        	p:loginUrl="/login"
        	p:successUrl="/login/success"
        	p:filters-ref="filters"
        	p:filterChainDefinitionMap-ref="filterChainDefinitionMap"*/
    
    
}
