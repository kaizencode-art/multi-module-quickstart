package com.example.shared;

import com.example.shared.rest.client.RandomNameClient;
import com.example.shared.rest.dto.NameResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class NameGenerator {

    @Inject
    GreetingTemplate template;

    @Inject
    @RestClient
    RandomNameClient randomNameClient;


    public String rangdomFristName() {
        NameResponse response = randomNameClient.get("name");
        return response.results().get(0).name().first();
    }
}
