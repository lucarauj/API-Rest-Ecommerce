package br.com.api.view.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    Long id;
    String name;
    Integer amount;
    Double price;
    String description;
}
