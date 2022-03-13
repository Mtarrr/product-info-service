package com.github.mtarrr.pis.service;

import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;

import java.util.List;

public interface ProductOfferingService {

    ProductOfferingEntity createProductOffering(ProductOfferingEntity productOffering);

    ProductOfferingEntity patchProductOffering(String id, ProductOfferingEntity productOffering);

    List<ProductOfferingEntity> getAllProductOfferings();

    ProductOfferingEntity getProductOfferingById(String id);

    void deleteProductOffering(String id);
}
