package com.example.shared.rest.client;


import com.example.shared.rest.dto.NameResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api")
@RegisterRestClient(configKey = "random-name-api")
public interface RandomNameClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    NameResponse get(@QueryParam("inc") String inc);
}
