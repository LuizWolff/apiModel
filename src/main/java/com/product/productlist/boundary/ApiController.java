package com.product.productlist.boundary;

//import com.product.productlist.control.DummyJsonApi;
import com.product.productlist.control.ApiProductService;
import com.product.productlist.entity.ApiProduct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
// Com essa anotação a classe já consegue receber aquisições do POSTMAN/Front-END
// O Controller recebe as aquisições do front-end/clientes
public class ApiController {

    private final List<ApiProduct> apiProductList;
    private final ModelMapper modelMapper;
//    private final DummyJsonApi dummyJsonApi;
    private static Long CONTADOR = 2L;

    private final ApiProductService apiProductService;


    public ApiController(List<ApiProduct> apiProductList, ModelMapper modelMapper, ApiProductService apiProductService) {
        //public ApiController(List<ApiProduct> apiProductList, ModelMapper modelMapper, DummyJsonApi dummyJsonApi, ApiProductService apiProductService) {

            this.apiProductList = apiProductList;
        this.modelMapper = modelMapper;
//        this.dummyJsonApi = dummyJsonApi;
        this.apiProductService = apiProductService;
    }

    @GetMapping("/apiproducts") //URI
    public List<ApiProductResponseDto> getProducts(){
        List<ApiProductResponseDto> productsResponseDto = apiProductService.pegarProduct();

        return productsResponseDto;
    }

    @GetMapping("/apiproducts/{id}")
    public ApiProductResponseDto getProductPorId(@PathVariable Long id) {
        for (ApiProduct product : apiProductList) {
            if (product.getId().equals(id)) {
                return modelMapper.map(product, ApiProductResponseDto.class);
            }
        }
        return null;
    }

    @GetMapping("/apiproducts/descricao")
    public List<ApiProduct> getProductPorDescricao(@RequestParam String desc) {
        List products = apiProductList.stream().filter(product -> product.getDescricao().contains(desc)).toList();

        return products;
    }


    @PostMapping("/apiproducts")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiProductResponseDto criarProducts(@Valid @RequestBody ApiProductRequestDto productRequestDto) {
      ApiProductResponseDto productDto = apiProductService.saveProduct(productRequestDto);

        return productDto;

    }

    @DeleteMapping("/apiproducts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable @Valid @NotNull Long id){
        apiProductService.apagarProductPorId(id);
    }

    @PutMapping("/apiproducts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiProductResponseDto editarProduct(@PathVariable Long id, @Valid @RequestBody ApiProductRequestDto requestDto){

        ApiProductResponseDto productResponseDto = apiProductService.editarProduct(id, requestDto);

        return productResponseDto;
    }

    @PatchMapping("/apiproducts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editarParcialmenteProduct(@PathVariable Long id, @RequestBody ApiProductRequestDto requestDto){

        apiProductService.editarParcilamente(id, requestDto);

    }
//
//    @GetMapping("/dummyapi/products")//@RequestParam Long max, @RequestParam Long pulaItems
//    public ApiModelResponse getProductsExternalApi(){
//
//        ApiModelResponse products = dummyJsonApi.getProducts();
//
//        return products;
//
//    }
//
//    @GetMapping("/dummyapi/products/{id}")
//    public ApiProductResponseDto getProductExternalApiPorId(@PathVariable Long id){
//        ApiProductItemResponse product = dummyJsonApi.getProductPorId(id);
//
//        ApiProductResponseDto productDto = new ApiProductResponseDto();
//
//        productDto.setId(product.getId());
//        product.setTittle(product.getTittle());
//        product.setDescription(product.getDescription());
//
//        return productDto;
//
//    }



}
