package com.singularai.watty.rest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by vamsi on 13/03/17.
 */
@ApplicationPath("/")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("com.singularai.watty.rest");
        register(ParamExceptionMapper.class);
    }
}