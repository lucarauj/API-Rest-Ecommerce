package br.com.api.service;

import br.com.api.exception.ResourceNotFoundException;
import br.com.api.model.Product;
import br.com.api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> searchProductById(Long id) {
        var searchProduct = productRepository.findById(id);
        if (searchProduct.isPresent()) {
            return productRepository.findById(id);
        }
        throw new ResourceNotFoundException("Produto não encontrado");
    }

    public Product updateProduct(Product product, Long id) {
        var searchProduct = productRepository.findById(id);
        if(searchProduct.isPresent()) {
            var updateProduct = productRepository.getReferenceById(id);
            updateProduct.setName(product.getName());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setAmount(product.getAmount());
            return productRepository.save(updateProduct);
        }

        throw new ResourceNotFoundException("Produto não encontrado");
    }

    public void deleteProduct(Long id) {
        var searchProduct = productRepository.findById(id);
        if (searchProduct.isPresent()) {
            productRepository.deleteById(id);
        }
        throw new ResourceNotFoundException("Produto não encontrado");
    }
}
