package com.revature.authentication;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.revature.exceptions.ApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtTokenProvider {
	
	
	private final Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	@Value("8640000")
	private String jwtExpirationInMs;
	
	@Value("Bearer")
	private String jwtTokenPrefix;
	
	@Value("Authorization")
	private String jwtHeaderString;
	
	
	
	
	//this method generate token
	public String generateToken(Authentication auth)  throws UnsupportedEncodingException {
		String authorities = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining());
		
		 //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    
	    String id = UUID.randomUUID().toString();
	    String subject = auth.getName();		
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder()
	    							.setId(id)
	    							.setSubject(subject)
	                                .setIssuedAt(now)
	                                .claim("roles", authorities);
	    
		builder.signWith(jwtSecret,signatureAlgorithm);
	    //if it has been specified, let's add the expiration
	    
	    long expMillis = nowMillis + 8640000;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    
	   
	        return builder.compact();
		
	}
	
	
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = resolveToken(request);
		if(token == null) {
			return null;
		}
		String username;
		Claims claims;
			claims = (Claims) Jwts.parser().setSigningKey(jwtSecret).parse(token).getBody();
			username = claims.getSubject();
		
		
		final List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
				.map(role -> role.startsWith("ROLE_")?role:"ROLE_"+role)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return username != null? new UsernamePasswordAuthenticationToken(username,null,authorities):null;
	}
	
	public boolean validateToken(HttpServletRequest request) {
		String token = resolveToken(request);
		if(token == null) {
			return false;
		}
		
		Claims claims = (Claims) Jwts.parser().setSigningKey(jwtSecret).parse(token).getBody();
 
		if(claims.getExpiration().before(new Date())) {
			return false;
		}
		return true;
	}
	
	private String resolveToken(HttpServletRequest req) {
		//Bearer key
		String bearerToken = req.getHeader(jwtHeaderString);
		if(bearerToken!=null && bearerToken.startsWith(jwtTokenPrefix)) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
