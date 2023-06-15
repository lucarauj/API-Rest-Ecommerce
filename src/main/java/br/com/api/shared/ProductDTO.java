package br.com.api.shared;

public record ProductDTO(
        Long id,
        String name,
        Integer amount,
        Double price,
        String description) {

    public ProductDTO updateProduct(ProductDTO dto, Long id) {
        return new ProductDTO(id, dto.name(), dto.amount(), dto.price(), dto.description());
    }
}
