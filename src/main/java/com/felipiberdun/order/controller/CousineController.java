package com.felipiberdun.order.controller;

import com.felipiberdun.order.dto.output.CousineDto;
import com.felipiberdun.order.dto.output.StoreDto;
import com.felipiberdun.order.service.CousineService;
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
public class CousineController {

    private final CousineService cousineService;

    @Autowired
    public CousineController(final CousineService cousineService) {
        this.cousineService = cousineService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/cousines", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CousineDto> findAll() {
        return cousineService.find();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/cousines/search/{searchText}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CousineDto> findByDescription(@PathVariable("searchText") final String criteria) {
        return cousineService.find();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/cousines/{id}/stores")
    public List<StoreDto> findStores(@PathVariable("id") final Long cousineId) {
        return cousineService.findStoresByCousine(cousineId);
    }

}
