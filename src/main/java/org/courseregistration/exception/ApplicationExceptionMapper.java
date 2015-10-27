package org.courseregistration.exception;

import com.google.common.collect.Maps;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

    public Response toResponse(ApplicationException ex) {
        Map<String,ApplicationException> exMap = new HashMap<>();
        exMap.put("errors",ex);
        return Response.status(ex.getStatus())
            .entity(exMap)
            .type(MediaType.APPLICATION_JSON)
            .build();
    }
}
