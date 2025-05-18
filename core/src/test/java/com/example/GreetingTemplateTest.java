package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GreetingTemplateTest {

    GreetingTemplate template;

    @BeforeEach
    void init() {
        template = new GreetingTemplate("Yo");
    }

    @Test
    void should_format_message_with_given_prefix() {

        assertEquals("Yo Jhon", template.format("Jhon"));
    }

    @Test
    void should_have_space_between_prefix_and_name() {
        String name = "Alice";

        String result = template.format(name);

        assertTrue(result.matches(".*\\s" + name));
    }
}
