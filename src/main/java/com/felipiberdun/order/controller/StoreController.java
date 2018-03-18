package com.felipiberdun.order.controller;

import com.felipiberdun.order.domain.Product;
import com.felipiberdun.order.dto.output.StoreDto;
import com.felipiberdun.order.service.StoreService;
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
@RestController
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(final StoreService storeService) {
        this.storeService = storeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/stores", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<StoreDto> find() {
        return storeService.find();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/stores/search/{searchText}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<StoreDto> findByName(@PathVariable("searchText") final String criteria) {
        //TODO CREATE FILTER
        return storeService.find();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/stores/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public StoreDto findById(@PathVariable("id") final Long id) {
        return storeService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/stores/{id}/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> findProductsByStore(final Long storeId) {
        return storeService.findProducts(storeId);
    }

}
