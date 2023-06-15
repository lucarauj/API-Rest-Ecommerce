package br.com.api.view.model;

public record ProductRequest(
        String name,
        Integer amount,
        Double price,
        String description
) {
}
