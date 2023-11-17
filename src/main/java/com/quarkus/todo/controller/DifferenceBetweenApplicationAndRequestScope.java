package com.quarkus.todo.controller;

import com.quarkus.todo.service.DifferenceBetweenApplicationAndRequestScopeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("scope")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DifferenceBetweenApplicationAndRequestScope {

    @Inject
    DifferenceBetweenApplicationAndRequestScopeService service;

    @GET
    @Path("/{name}")
    public Response addToList(@PathParam("name") String name) {
        service.add(name);
        return Response
                .status(Status.ACCEPTED)
                .entity(service.lists())
                .build();
    }

    @GET
    @Path("/")
    public Response getList() {
        return Response
                .status(Status.OK)
                .entity(service.lists())
                .build();
    }
}
