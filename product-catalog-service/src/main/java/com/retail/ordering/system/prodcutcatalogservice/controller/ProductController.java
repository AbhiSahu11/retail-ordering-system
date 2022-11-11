package com.retail.ordering.system.prodcutcatalogservice.controller;

import com.retail.ordering.system.prodcutcatalogservice.entities.Product;
import com.retail.ordering.system.prodcutcatalogservice.exception.ProductNotFoundException;
import com.retail.ordering.system.prodcutcatalogservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Product Catalog API", description = " Product API is used to provide product details for Retail ordering System ")
@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @Operation(summary = "Get All Product Catalog from DB")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - Product List is retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @GetMapping("")
    public List<Product> allProducts() {
        return productService.findAllProducts();
    }


    @Operation(summary = "Get All Product Catalog from DB based on Product Code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - Product List is retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @GetMapping("/{code}")
    public Product productByCode(@PathVariable String code) {
        log.info("Getting product for product_code: {}",code);
        Optional<Product> product=productService.findProductByCode(code);
        return product.orElseThrow(() -> new ProductNotFoundException("Product with code {} doesn't exist",code));
    }


}
