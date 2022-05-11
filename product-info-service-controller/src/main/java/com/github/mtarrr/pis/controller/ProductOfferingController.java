package com.github.mtarrr.pis.controller;

import com.github.mtarrr.pis.mapper.ProductOfferingMapper;
import com.github.mtarrr.pis.model.ProductOffering;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import com.github.mtarrr.pis.service.ElasticServiceImpl;
import com.github.mtarrr.pis.api.service.ProductOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productManagement/productOffering")
@RequiredArgsConstructor
public class ProductOfferingController {

    private final ProductOfferingService productOfferingService;
    private final ElasticServiceImpl elasticService;

    private final ProductOfferingMapper productOfferingMapper = ProductOfferingMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<?> createProductOffering(@RequestBody ProductOffering productOffering) throws Exception {
        final ProductOfferingEntity entity = productOfferingService.createProductOffering(productOfferingMapper.map(productOffering));
        elasticService.saveToElastic(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(productOfferingMapper.map(entity));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProductOffering(@PathVariable("id") String id, @RequestBody ProductOffering productOffering) throws Exception {
        final ProductOfferingEntity entity = productOfferingService.patchProductOffering(id, productOfferingMapper.map(productOffering));
        elasticService.saveToElastic(entity);
        return ResponseEntity.status(HttpStatus.OK).body(productOfferingMapper.map(entity));
    }

    @GetMapping
    public ResponseEntity<?> getAllProductOfferings() throws Exception {
        final List<ProductOffering> list = productOfferingService.getAllProductOfferings().stream().map(productOfferingMapper::map).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductOfferingById(@PathVariable("id") String id) throws Exception {
        final ProductOffering productOffering = productOfferingMapper.map(productOfferingService.getProductOfferingById(id));
        return ResponseEntity.status(HttpStatus.OK).body(productOffering);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductOffering(@PathVariable("id") String id) throws Exception {
        productOfferingService.deleteProductOffering(id);
        elasticService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
