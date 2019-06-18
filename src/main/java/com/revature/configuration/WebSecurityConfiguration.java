package com.revature.configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.revature.authentication.DetailsService;
import com.revature.authentication.jwtAuthorizationFilter;
import com.revature.authentication.jwtTokenProvider;
import com.revature.models.User;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private jwtTokenProvider tokenProvider;
	
	@Autowired
	DetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(User.PASSWORD_ENCODER);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/api/user/login").permitAll()
				.anyRequest().authenticated()
				.and()
					.httpBasic()
				.and()
				.addFilterBefore(new jwtAuthorizationFilter(authenticationManager(), tokenProvider),BasicAuthenticationFilter.class)
				.sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					.csrf().disable();
	}
	
	
	
	

}
