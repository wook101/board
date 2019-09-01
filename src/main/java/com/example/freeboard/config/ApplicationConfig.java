package com.example.freeboard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"com.example.freeboard.dao","com.example.freeboard.serviceimpl"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
