package com.example.freeboard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"com.example.freeboard.daoImpl","com.example.freeboard.serviceImpl"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
