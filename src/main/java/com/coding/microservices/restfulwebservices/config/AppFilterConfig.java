package com.coding.microservices.restfulwebservices.config;

import com.jfilter.EnableJsonFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({"com.jfilter.components"})
@EnableJsonFilter
public class AppFilterConfig implements WebMvcConfigurer {
}
