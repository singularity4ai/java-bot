package com.singularai.watty.rest;

import org.glassfish.jersey.server.ParamException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by vamsi on 13/03/17.
 */
@Provider
public class ParamExceptionMapper implements ExceptionMapper<ParamException> {
    public Response toResponse(ParamException exception) {
        return Response.status(Response.Status.ACCEPTED)
                .entity(exception.getParameterName() + " incorrect type")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}