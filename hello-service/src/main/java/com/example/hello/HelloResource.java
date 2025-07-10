package com.example.hello;

import com.example.shared.GreetingTemplate;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/hello")
public class HelloResource {
    
    private final GreetingTemplate template;
    
    @Inject
    HelloResource(GreetingTemplate template) {
        this.template = template;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return template.format("World");
    }
}
