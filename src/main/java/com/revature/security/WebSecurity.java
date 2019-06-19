package com.revature.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.revature.services.UserServiceImpl;

import static com.revature.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	//private UserDetailsServiceImpl userDetailsService;
	private UserServiceImpl userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
    
    public WebSecurity(UserServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
        		.and().csrf()
        		.disable()
        		.authorizeRequests()
        		.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
        		.antMatchers( "/v2/api-docs",
        	            "/swagger-resources",
        	            "/swagger-resources/**",
        	            "/configuration/ui",
        	            "/configuration/security",
        	            "/swagger-ui.html",
        	            "/webjars/**",
        	            "/api/**").permitAll()
//        		.antMatchers("/api/*").permitAll()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()               				
                .anyRequest().authenticated()
                .and()
//                .exceptionHandling()
//    			.authenticationEntryPoint(
//    					new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//    			.and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       // http.exceptionHandling().authenticationEntryPoint().(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
  
  
}
