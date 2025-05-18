package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MessageService {
    
    private final GreetingTemplate template;
    private static final Logger logger = Logger.getLogger(MessageService.class);
    
    @Inject
    public MessageService(GreetingTemplate template) {
        this.template = template;
    }

    public String createMessage(String name) {
        logger.debug("Message service called");
        return template.format(name) + " !";
    }
}
