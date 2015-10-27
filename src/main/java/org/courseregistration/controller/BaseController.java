package org.courseregistration.controller;

import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
// @EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableEntityLinks
public class BaseController {
}
