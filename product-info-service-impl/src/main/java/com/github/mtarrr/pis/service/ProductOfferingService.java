package com.github.mtarrr.pis.service;

import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;

import java.util.List;

public interface ProductOfferingService {

    ProductOfferingEntity createProductOffering(ProductOfferingEntity productOffering) throws Exception;

    ProductOfferingEntity patchProductOffering(String id, ProductOfferingEntity productOffering) throws Exception;

    List<ProductOfferingEntity> getAllProductOfferings() throws Exception;

    ProductOfferingEntity getProductOfferingById(String id) throws Exception;

    void deleteProductOffering(String id) throws Exception;
}
