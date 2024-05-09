package com.product.productlist.config;


import com.product.productlist.entity.ApiProduct;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApiProductListApiConfig {// Serve para configurações iniciais do projeto

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

//    @Bean
//    public List<ApiProduct> ProductList(){
//        List productList = new ArrayList();
//
//
//        ApiProduct product = new ApiProduct(1L, "Miojo","Miojo da Monica de tomate",
//                1,8.5,50);
//
//        ApiProduct product2 = new ApiProduct(2L, "Macarrão","Massa de macarrão",
//                4,7.5,35);
//
//        productList.add(product);
//        productList.add(product2);
//
//        return productList;
//    }
}
