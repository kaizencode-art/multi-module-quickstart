package com.example.app;

import com.example.service.MessageService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/hello")
public class HelloResource {
    
    private final MessageService service;
    
    @Inject
    HelloResource(MessageService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return service.createMessage("Quarkus");
    }
}
