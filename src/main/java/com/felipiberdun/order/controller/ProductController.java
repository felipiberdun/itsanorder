package com.felipiberdun.order.controller;

import com.felipiberdun.order.dto.external.ProductDto;
import com.felipiberdun.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
@RestController(value = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDto> findAll() {
        return productService.find();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search/{searchText}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDto> find(@PathVariable("searchText") final String criteria) {
        //TODO Create filter
        return productService.find();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable("id") final Long id) {
        return productService.findById(id);
    }

}
