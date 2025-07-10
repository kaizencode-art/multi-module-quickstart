package com.example.shared;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@ApplicationScoped
public class GreetingTemplate {
    private final String prefix;
    private static final Logger logger = Logger.getLogger(GreetingTemplate.class);
    
    @Inject
    public GreetingTemplate(@ConfigProperty(name = "greeting.prefix", defaultValue = "Hello") String prefix) {
        this.prefix = prefix;
    }
    
    public String format(String name) {
        logger.debug("format retrieved");
        return prefix + " " + name ; 
    }
}
