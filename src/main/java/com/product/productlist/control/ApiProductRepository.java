package com.product.productlist.control;

import com.product.productlist.entity.ApiProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiProductRepository extends JpaRepository<ApiProduct,Long> {

}
