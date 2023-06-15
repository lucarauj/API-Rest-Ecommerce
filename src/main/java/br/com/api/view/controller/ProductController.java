package br.com.api.view.controller;

import br.com.api.service.ProductService;
import br.com.api.shared.ProductDTO;
import br.com.api.view.model.ProductRequest;
import br.com.api.view.model.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        ModelMapper mapper = new ModelMapper();
        ProductDTO dto = mapper.map(request, ProductDTO.class);
        dto = productService.createProduct(dto);
        return new ResponseEntity<>(mapper.map(dto, ProductResponse.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listProducts() {
        List<ProductDTO> products = productService.listProducts();
        ModelMapper mapper = new ModelMapper();
        List<ProductResponse> responseList = products
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> searchProductById(@PathVariable Long id) {
        Optional<ProductDTO> productDTO = productService.searchProductById(id);
        ProductResponse response = new ModelMapper().map(productDTO.get(), ProductResponse.class);
        return new ResponseEntity<>(Optional.of(response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest request, @PathVariable Long id) {
        ModelMapper mapper = new ModelMapper();
        ProductDTO dto = mapper.map(request, ProductDTO.class);
        dto = productService.updateProduct(dto, id);
        return new ResponseEntity<>(mapper.map(dto, ProductResponse.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
