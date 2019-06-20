package com.revature.configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.revature.authentication.DetailsService;
import com.revature.authentication.jwtAuthorizationFilter;
import com.revature.authentication.jwtTokenProvider;
import com.revature.models.User;


@EnableWebSecurity
public class MultiHttpSecurityConfig {
	@Configuration
    @Order(1)                                                        
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("register")                               
                .authorizeRequests()
                    .anyRequest().permitAll().and()
					.httpBasic();
        }
    }
	@Configuration
		
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
			http	.cors().and().csrf().disable()	
					.antMatcher("/api/**").authorizeRequests()
					.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
						.anyRequest().authenticated()
						.and()
					.httpBasic()
					.and()
						.addFilterBefore(new jwtAuthorizationFilter(authenticationManager(), tokenProvider),BasicAuthenticationFilter.class)
						.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			
		}
		@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
		
		
		
		

	}
}