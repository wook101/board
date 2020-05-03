package com.example.freeboard.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity // EnableWebSecurity annotation이 있어야함
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable();	//CSRF 공격을 막기 위해 설정하는 부분 disable처리 안하면 (ajax post요청시 403)에러발생
	/*
			.authorizeRequests()												//권한을 줄 경우 사용
				.antMatchers("/favicon.ico").permitAll()
				.and()
				//.antMatchers("/write").hasRole("USER")					
			.logout()																//로그아웃
				.logoutSuccessUrl("/login");							
			//	.and();
			//.formLogin()
			///	.loginPage("/login");										//login.jsp
				//.loginProcessingUrl("/perform_login")
				//.defaultSuccessUrl ("/board",true);		

	*/

	}
	
	//BCrypt패스워드 인코더 빈 설정
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	
}
