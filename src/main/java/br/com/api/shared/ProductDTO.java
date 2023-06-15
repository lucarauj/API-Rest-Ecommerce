package br.com.api.shared;

import lombok.Setter;

@Setter
public record ProductDTO(
        Long id,
        String name,
        Integer amount,
        Double price,
        String description) {
}
