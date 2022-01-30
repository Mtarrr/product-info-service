package com.github.mtarrr.pis.controller.productManagement.productOffering;

import com.github.mtarrr.pis.mapper.ProductOfferingMapper;
import com.github.mtarrr.pis.model.ProductOffering;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import com.github.mtarrr.pis.service.ProductOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductOfferingController {

    private final ProductOfferingService productOfferingService;

    private final ProductOfferingMapper productOfferingMapper = ProductOfferingMapper.INSTANCE;

    @PostMapping
    //отправка сущности
    public ResponseEntity<?> createProductOffering(@RequestBody ProductOffering productOffering) {
        final ProductOfferingEntity entity = productOfferingService.createProductOffering(productOfferingMapper.map(productOffering));
        return ResponseEntity.status(HttpStatus.CREATED).body(productOfferingMapper.map(entity));
    }

    @PatchMapping("/{id}")
    //частичное изменение сущности
    public ResponseEntity<?> patchProductOffering(@PathVariable("id") String id, @RequestBody ProductOffering productOffering) {
        final ProductOfferingEntity entity = productOfferingService.patchProductOffering(productOfferingMapper.map(productOffering));
        return ResponseEntity.status(HttpStatus.OK).body(productOfferingMapper.map(entity));
    }

    @GetMapping
    //получение всех сущностей
    public ResponseEntity<?> getAllProductOfferings() {
        final List<ProductOffering> list = productOfferingService.getAllProductOfferings().stream().map(productOfferingMapper::map).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    //получение сущности по id
    public ResponseEntity<?> getProductOfferingById(@PathVariable("id") String id) {
        final ProductOffering productOffering = productOfferingMapper.map(productOfferingService.getProductOfferingById(id));
        return ResponseEntity.status(HttpStatus.OK).body(productOffering);
    }

    @DeleteMapping("/{id}")
    //удаление сущности по id
    public ResponseEntity<?> deleteProductOffering(@PathVariable("id") String id) {
        productOfferingService.deleteProductOffering(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
