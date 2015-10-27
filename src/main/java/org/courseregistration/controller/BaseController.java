package org.courseregistration.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.ws.rs.ApplicationPath;

@Configuration
@EnableWebMvc
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
class BaseController extends WebMvcConfigurerAdapter {
    /*@Bean
    RelProvider relProvider() {
        new JsonRootRelProvider();
    }*/
}
