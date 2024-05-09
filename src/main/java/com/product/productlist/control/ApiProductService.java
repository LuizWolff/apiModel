package com.product.productlist.control;

import com.product.productlist.boundary.ApiProductRequestDto;
import com.product.productlist.boundary.ApiProductResponseDto;
import com.product.productlist.entity.ApiProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiProductService {
    private final ApiProductRepository productRepository;
//    private final DummyJsonApi dummyJsonApi;

    public ApiProductService(ApiProductRepository productRepository){
    //public ApiProductService(ApiProductRepository productRepository, DummyJsonApi dummyJsonApi){
        this.productRepository = productRepository;
//        this.dummyJsonApi = dummyJsonApi;
    }

    public ApiProductResponseDto saveProduct(ApiProductRequestDto productDto){
        ApiProduct product = new ApiProduct();

        product.setTitle(productDto.getTitle());
        product.setDescricao(productDto.getDescricao());
        product.setPrice(productDto.getPrice());
        product.setRating(productDto.getRating());
        product.setStock(productDto.getStock());

       ApiProduct savedProduct = productRepository.save(product);

       ApiProductResponseDto responseDto = new ApiProductResponseDto(savedProduct.getId(), savedProduct.getDescricao());

        return responseDto;
    }

    public List<ApiProductResponseDto> pegarProduct() {
        List<ApiProduct> products = productRepository.findAll();

        List<ApiProductResponseDto> productResponseDtos  = products.stream().map(product ->
                new ApiProductResponseDto(product.getId(), product.getDescricao())).toList();

        return productResponseDtos;
    }

    public void apagarProductPorId(Long id) {
        productRepository.deleteById(id);
    }

    public ApiProductResponseDto editarProduct(Long id, ApiProductRequestDto requestDto) {

        Optional<ApiProduct> productEncontrado = productRepository.findById(id);

        if(productEncontrado.isPresent()){
            ApiProduct product = productEncontrado.get();

            product.setTitle(requestDto.getTitle());
            product.setDescricao(requestDto.getDescricao());
            product.setPrice(requestDto.getPrice());
            product.setStock(requestDto.getStock());
            product.setRating(requestDto.getRating());

            ApiProduct productEditado = productRepository.save(product);

            return new ApiProductResponseDto(productEditado.getId(), productEditado.getDescricao());
        }

        return null;
    }

    public void editarParcilamente(Long id, ApiProductRequestDto requestDto) {
        Optional<ApiProduct> productEncontrado = productRepository.findById(id);

        if(productEncontrado.isPresent()){
            ApiProduct product = productEncontrado.get();

            if(requestDto.getTitle() != null) {
                product.setTitle(requestDto.getTitle());
            }

            if(requestDto.getDescricao() != null) {
                product.setDescricao(requestDto.getDescricao());
            }

            if (requestDto.getPrice() != null ) {
                product.setPrice(requestDto.getPrice());
            }

            if (requestDto.getStock() != null) {
                product.setStock(requestDto.getStock());
            }

            if (requestDto.getRating() != null) {
                product.setRating(requestDto.getRating());
            }

            ApiProduct productEditado = productRepository.save(product);
        }
    }

}
