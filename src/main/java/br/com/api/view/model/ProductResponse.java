package br.com.api.view.model;

public record ProductResponse(
        Long id,
        String name,
        Integer amount,
        Double price,
        String description
) {
}
