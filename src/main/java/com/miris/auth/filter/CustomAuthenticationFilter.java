package com.miris.auth.filter;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter { 

	
	private final AuthenticationManager authenticationManager;
	private final RedisTemplate<String, Object> redisTemplate;

	@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
	    String username = request.getParameter("username");
        String password = request.getParameter("password");
        


        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

//        return authenticationManager.authenticate(authRequest);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(obtainUsername(request), obtainPassword(request))
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
    	
    	HashMap<String, Object> responseInfo = new HashMap<String, Object>(
        );
    	HttpSession session = request.getSession();
 
    	responseInfo.put("principal", authResult.getPrincipal());
    	responseInfo.put("session", session.getId());
    	responseInfo.put("expiredTime", session.getMaxInactiveInterval());
    	
    	
    	log.info("getMaxInactiveInterval :" +  session.getMaxInactiveInterval());
    	log.info("getLastAccessedTime :" +  session.getLastAccessedTime());
    	log.info("getCreationTime :" +  session.getCreationTime());
    	
    	
    	
        new ObjectMapper().writeValue(
                response.getOutputStream(), responseInfo);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"Login Failed\"}");
        response.getWriter().flush();
    }

}
