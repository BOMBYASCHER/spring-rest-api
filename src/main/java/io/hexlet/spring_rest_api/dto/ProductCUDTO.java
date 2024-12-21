package io.hexlet.spring_rest_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCUDTO {
    private String title;
    private int price;
    private String description;
    private Boolean availability;
    private String image;
}
