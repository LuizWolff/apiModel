package com.product.productlist.control;

import com.product.productlist.boundary.ApiModelResponse;
import com.product.productlist.boundary.ApiProductItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//    @FeignClient(name="productdummyjson", url ="https://dummyjson.com")
//public interface DummyJsonApi {
//
//    @GetMapping("/products")//@RequestParam Long limit, @RequestParam Long skip
//    ApiModelResponse getProducts();
//
//    @GetMapping("products/{id}")
//    ApiProductItemResponse getProductPorId(@PathVariable Long id);
//
//
//
//}
