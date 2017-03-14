package com.singularai.watty.rest;

import com.singularai.watty.rest.annotations.AuthenticationRequired;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import java.lang.reflect.Method;

/**
 * Created by vamsi on 13/03/17.
 */
public class AuthenticationFeature implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        Class _class = resourceInfo.getResourceClass();
        Method method = resourceInfo.getResourceMethod();
        if (method.isAnnotationPresent(AuthenticationRequired.class) || _class.isAnnotationPresent(AuthenticationRequired.class)) {
            AuthenticationFilter authenticationFilter = new AuthenticationFilter();
            context.register(authenticationFilter);
        }
    }
}

