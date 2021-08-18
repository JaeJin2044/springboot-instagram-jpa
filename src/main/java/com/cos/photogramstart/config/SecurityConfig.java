package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity    //해당 파일로 시큐리티를 활성화 
@Configuration 	      //IOC에 저장 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean //IOC가 빈으로 등록하고 들고 Bcrypt 객체를 들고 있음
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// SUPER 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화 됨.
		// STEP1) 이제 내가 주소설정을 할 수 있게 설정해야함.
		
		http.csrf().disable(); //CSRF토큰 비활성화 (POSTMAN으로 요청하든 ... 정상적으로 요청하든 나는 구분하지 않겠다.)
		
		
		
		http.authorizeRequests()
		.antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated() //해당  url접근시 인증해야함..
		.anyRequest().permitAll()  // 나머지는 허가해준다.
		.and()
		.formLogin()   
		.loginPage("/auth/signin")   // 로그인 페이지 : 해당 url GET으로 들어옴
		.loginProcessingUrl("/auth/signin")  //POST -> 스프링시큐리티가 로그인 프로세스 진행(이 요청이 들어오면 시큐리티가 낚아챈다.)
		.defaultSuccessUrl("/");      // 로그인을 정상적으로 처리시에 해당하는 URL  
		
	}
}
