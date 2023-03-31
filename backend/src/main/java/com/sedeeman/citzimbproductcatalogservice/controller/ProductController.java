package com.sedeeman.citzimbproductcatalogservice.controller;

import com.sedeeman.citzimbproductcatalogservice.model.Product;
import com.sedeeman.citzimbproductcatalogservice.repository.ProductRepository;
import com.sedeeman.citzimbproductcatalogservice.request.ProductCreateRequest;
import com.sedeeman.citzimbproductcatalogservice.request.ProductUpdateRequest;
import com.sedeeman.citzimbproductcatalogservice.response.SuccessResponse;
import com.sedeeman.citzimbproductcatalogservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService,
                             ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    /**
     * Get all products list.
     *
     * @return List of products
     */
    @GetMapping
    @Operation(description = "Get a list of all products", responses = {
            @ApiResponse(
                    responseCode = "200",
                    ref = "getAllSuccessAPI"
            ),
            @ApiResponse(
                    responseCode = "204",
                    ref = "noContentAPI"
            ),
            @ApiResponse(
                    responseCode = "500",
                    ref = "internalServerErrorAPI"
            )

    })
    public ResponseEntity<SuccessResponse> getAllProducts() {

        List<Product> products = productService.getAllProducts();
        if (CollectionUtils.isEmpty(products)) {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", "Products are not available");

            return new ResponseEntity<>(new SuccessResponse(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase(), "Products are not available", products), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Successfully retrieved products", products), HttpStatus.OK);
    }


    /**
     * Get Product by id.
     *
     * @param productId
     * @return Product
     */
    @GetMapping(value = "/{productId}")
    @Operation(description = "Get a product by productId", responses = {
            @ApiResponse(
                    responseCode = "200",
                    ref = "getSuccessAPI"
            ),
            @ApiResponse(
                    responseCode = "400",
                    ref = "badRequestAPI"
            ),
            @ApiResponse(
                    responseCode = "404",
                    ref = "notFoundAPI"
            ),
            @ApiResponse(
                    responseCode = "500",
                    ref = "internalServerErrorAPI"
            )

    })
    public ResponseEntity<SuccessResponse> getProductById(@PathVariable Long productId) {

        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Successfully retrieved product", product), HttpStatus.OK);
    }


    /**
     * Add a new Product.
     *
     * @param productCreateRequest
     * @return Product
     */
    @PostMapping
    @Operation(description = "Add a new product", responses = {
            @ApiResponse(
                    responseCode = "201",
                    ref = "postSuccessAPI"
            ),
            @ApiResponse(
                    responseCode = "400",
                    ref = "badRequestAPI"
            ),
            @ApiResponse(
                    responseCode = "500",
                    ref = "internalServerErrorAPI"
            )

    })
    public ResponseEntity<SuccessResponse> createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest) {

        Product savedProduct = productService.createProduct(productCreateRequest);
        return new ResponseEntity<>(new SuccessResponse<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), "Add a new product", savedProduct), HttpStatus.CREATED);
    }


    /**
     * Update a Product.
     *
     * @param productId
     * @param productUpdateRequest
     * @return Product
     */
    @PutMapping(value = "/{productId}")
    @Operation(description = "Update a product by productId", responses = {
            @ApiResponse(
                    responseCode = "200",
                    ref = "putSuccessAPI"
            ),
            @ApiResponse(
                    responseCode = "400",
                    ref = "badRequestAPI"
            ),
            @ApiResponse(
                    responseCode = "404",
                    ref = "notFoundAPI"
            ),
            @ApiResponse(
                    responseCode = "500",
                    ref = "internalServerErrorAPI"
            )

    })
    public ResponseEntity<SuccessResponse> updateProduct(@PathVariable Long productId,
                                                         @RequestBody @Valid ProductUpdateRequest productUpdateRequest) {

        Product updatedProduct = productService.updateProduct(productId, productUpdateRequest);
        return new ResponseEntity<>(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Successfully updated product", updatedProduct), HttpStatus.OK);
    }


    /**
     * Delete a Product.
     *
     * @param productId
     * @return {}
     */
    @DeleteMapping(value = "/{productId}")
    @Operation(description = "Delete a product by productId", responses = {
            @ApiResponse(
                    responseCode = "204",
                    ref = "deleteSuccessAPI"
            ),
            @ApiResponse(
                    responseCode = "400",
                    ref = "badRequestAPI"
            ),
            @ApiResponse(
                    responseCode = "404",
                    ref = "notFoundAPI"
            ),
            @ApiResponse(
                    responseCode = "500",
                    ref = "internalServerErrorAPI"
            )

    })
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "productId") Long productId) {

        productService.removeProduct(productId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Successfully deleted product");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
    }


}
