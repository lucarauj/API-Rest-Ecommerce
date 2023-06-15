package br.com.api.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO{

    Long id;
    String name;
    Integer amount;
    Double price;
    String description;

}
