package com.github.mtarrr.pis.controller.productManagement.productOffering;

import com.github.mtarrr.pis.entity.ProductOfferingEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductOfferingController {

    @PostMapping
    //отправка сущности
    public ResponseEntity<?> createProductOffering(@RequestBody ProductOfferingEntity productOffering) {
        return null;
    }

    @PatchMapping("/{id}")
    //частичное изменение сущности
    public ResponseEntity<?> patchProductOffering(@PathVariable("id") String id, @RequestBody ProductOfferingEntity productOffering) {
        return null;
    }

    @GetMapping
    //получение всех сущностей
    public ResponseEntity<?> getAllProductOfferings() {
        return null;

    }

    @GetMapping("/{id}")
    //получение сущности по id
    public ResponseEntity<?> getProductOfferingById(@PathVariable("id") String id) {
        return null;
    }

    @DeleteMapping("/{id}")
    //удаление сущности по id
    public ResponseEntity<?> deleteProductOffering(@PathVariable("id") String id) {
        return null;
    }
}
