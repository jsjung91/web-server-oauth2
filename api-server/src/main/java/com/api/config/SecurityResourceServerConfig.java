package com.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class SecurityResourceServerConfig extends ResourceServerConfigurerAdapter {
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/rest/v1/user/create", "/rest/v1/user/logout").permitAll()
                .antMatchers("/rest/v1/**")
                .access("#oauth2.hasScope('openid')");
    }
    
}