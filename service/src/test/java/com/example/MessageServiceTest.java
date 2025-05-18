package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

class MessageServiceTest {


    @Inject
    GreetingTemplate template;
    @BeforeEach
    void init() {
        template = new GreetingTemplate("Yo");
    }

    @Test
    void should_return_salutation_with_prefix_confoigured_and_given_name() {
        MessageService service = new MessageService(template);

        assertEquals("Yo Alice !", service.createMessage("Alice"));
    }
}
