package com.miris.auth.config;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

import com.miris.auth.filter.CustomAuthenticationFilter;
import com.miris.auth.service.PrincipalDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @FileName : SecurityConfiguration.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 13

 * @프로그램 설명 : Spring Security 필터 등록 및 cors 등 

 * @변경이력 :
 */
@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final PrincipalDetailService userDetails;
	private final RedisTemplate<String, Object> redisTemplate;
	
	
	/**
	 * 
	 * @Method Name : corsConfigurationSource
	
	 * @작성자 : yg87.kim
	
	 * @작성일 : 2024. 06. 13
	
	 * @프로그램 설명 : cors 설정 및 등
	
	 * @변경이력 :
	 */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	    
    	//UsernamePasswordAuthenticationFilter 커스
    	CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(),redisTemplate);
        
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        customAuthenticationFilter.setUsernameParameter("username");
        customAuthenticationFilter.setPasswordParameter("password");
        
        
        //cors 설정 등록
        http.cors().configurationSource(corsConfigurationSource());
        
        //로그인
    	http
        .csrf().disable()
        .authorizeRequests()
        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/**/*").permitAll()
        .anyRequest()
        .permitAll();
    	
    	//로그 아웃
        http.logout()
        .logoutUrl("/api/logout")
        .logoutSuccessHandler(new LogoutSuccessHandler() {
			
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				// TODO Auto-generated method stub
				log.info("authentication:" + authentication );
				log.info("requesParam: " + request.getParameter("username"));
			}
		})
        .logoutSuccessUrl("/logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID");

    	http.addFilter(customAuthenticationFilter);
    	http.userDetailsService(userDetails);
    	
    }
    
    @Bean
	public BCryptPasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
    
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetails).passwordEncoder(encoder());
    }

}

