package com.github.mtarrr.pis.controller;

import com.github.mtarrr.pis.api.service.ProductOfferingService;
import com.github.mtarrr.pis.mapper.ProductOfferingMapper;
import com.github.mtarrr.pis.model.ProductOffering;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import com.github.mtarrr.pis.utils.MyEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/productManagement/productOffering")
@RequiredArgsConstructor
public class ProductOfferingController {

    private final ProductOfferingService productOfferingService;
    private final ProductOfferingMapper productOfferingMapper = ProductOfferingMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<?> createProductOffering(@RequestBody ProductOffering productOffering) {
        final ProductOfferingEntity entity = productOfferingService.createProductOffering(productOfferingMapper.map(productOffering));
        return ResponseEntity.status(HttpStatus.CREATED).body(productOfferingMapper.map(entity));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProductOffering(@PathVariable("id") String id, @RequestBody ProductOffering productOffering) {
        final ProductOfferingEntity entity;
        try {
            entity = productOfferingService.patchProductOffering(id, productOfferingMapper.map(productOffering));
        } catch (NullPointerException e) {
            log.debug("Unable to update entity with id " + id);
            throw new MyEntityNotFoundException(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(productOfferingMapper.map(entity));
    }

    @GetMapping
    public ResponseEntity<?> getAllProductOfferings() {
        final List<ProductOffering> list = productOfferingService.getAllProductOfferings().stream().map(productOfferingMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductOfferingById(@PathVariable("id") String id) {
        final ProductOffering productOffering;
        try {
            productOffering = productOfferingMapper.map(productOfferingService.getProductOfferingById(id));
        } catch (NullPointerException e) {
            log.debug("Impossible to get entity with id " + id);
            throw new MyEntityNotFoundException(id);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(productOffering);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductOffering(@PathVariable("id") String id) {
        try {
            productOfferingService.deleteProductOffering(id);
        } catch (NullPointerException e) {
            log.debug("Unable to delete entity with id " + id);
            throw new MyEntityNotFoundException(id);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
