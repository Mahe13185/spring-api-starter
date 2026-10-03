package com.codewithmosh.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductDto {
    private Byte id;
    private String name;
    private BigDecimal price;
    private String description;
    private int categoryId;


}
