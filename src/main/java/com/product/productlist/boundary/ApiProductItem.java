package com.product.productlist.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiProductItem {

    private Long id;
    private String title;
    private String description;

}


