package com.example.freeboard.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer{
	private String driverClassName = "com.mysql.jdbc.Driver";
	/*
	//호스팅서버 연결계정
	private String url = "";
	private String username = "";
	private String password = "";
	*/
	//로컬서버 연결계정	
	private String url = "jdbc:mysql://localhost:3306/freeboard_db?useUnicode=true&characterEncoding=utf8";
	private String username = "connectuser2";
	private String password = "connect123!@#";
	
	//db연결은 위한 dataSource 
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	//플랫폼 트랜잭션 매니저
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
