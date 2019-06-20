package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.revature.repositories.UserRepository;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class InventoryApiApplication {
	
	 @Bean
	  public BCryptPasswordEncoder bCryptPasswordEncoder() {
	      return new BCryptPasswordEncoder();
	  }
	 
//	 @Bean
//	 public AuthenticationManager authenticationManager() {
//		 return new AuthenticationManager() {
//			
//			@Override
//			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//	 }
	 
//	 @Bean
//		public Docket api() {
//			return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//					.paths(PathSelectors.any()).build();
//		}

	public static void main(String[] args) {
		SpringApplication.run(InventoryApiApplication.class, args);	
	}

}
