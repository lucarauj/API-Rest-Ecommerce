package br.com.api.service;

import br.com.api.exception.ResourceNotFoundException;
import br.com.api.model.Product;
import br.com.api.repository.ProductRepository;
import br.com.api.shared.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(ProductDTO dto) {
        dto.setId(null);
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(dto, Product.class);
        product = productRepository.save(product);
        dto = mapper.map(product, ProductDTO.class);
        return dto;
    }

    public List<ProductDTO> listProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> new ModelMapper()
                .map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> searchProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        return Optional.of(dto);
    }

    public ProductDTO updateProduct(ProductDTO dto, Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        dto.setId(id);
        ModelMapper mapper = new ModelMapper();
        Product productUpdate = mapper.map(dto, Product.class);
        productRepository.save(productUpdate);
        return dto;
    }

    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        productRepository.deleteById(id);
    }
}
