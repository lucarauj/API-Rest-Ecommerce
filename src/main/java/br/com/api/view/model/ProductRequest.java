package br.com.api.view.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    String name;
    Integer amount;
    Double price;
    String description;
}
