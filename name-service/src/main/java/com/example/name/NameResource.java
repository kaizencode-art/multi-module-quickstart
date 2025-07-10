package com.example.name;

import com.example.shared.GreetingTemplate;
import com.example.shared.NameGenerator;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/name")
public class NameResource {
    
    private final com.example.shared.GreetingTemplate template;
    private final NameGenerator nameGenerator;
    
    @Inject
    NameResource(GreetingTemplate template, NameGenerator nameGenerator) {
        this.template = template;
        this.nameGenerator = nameGenerator;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String naame() {
        String firstName = nameGenerator.rangdomFristName();
        return template.format(nameGenerator.rangdomFristName());
    }
}
