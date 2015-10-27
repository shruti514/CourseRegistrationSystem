package org.courseregistration.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    public Response toResponse(Throwable ex) {
        Map<String,Throwable> exMap = new HashMap<>();
        exMap.put("errors",ex);

        GenericException exception = new GenericException();
        setHttpStatus(ex, exception);
        exception.setCode(1009);
        exception.setTittle(ex.getMessage());
        exception.setDetail(ex.getMessage());
        exception.setLink("");

        return Response.status(exception.getStatus())
            .entity(exMap)
            .type(MediaType.APPLICATION_JSON)
            .build();
    }

    private void setHttpStatus(Throwable ex, GenericException exception) {
        if(ex instanceof WebApplicationException) {
            exception.setStatus(((WebApplicationException)ex).getResponse().getStatus());
        } else {
            exception.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }
}


