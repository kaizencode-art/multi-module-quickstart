package com.example.shared.rest.dto;

import java.util.List;

public record NameResponse(List<Results> results) {
    public record Results(Name name) { }

    public record Name(String title, String first, String last) { }
}
