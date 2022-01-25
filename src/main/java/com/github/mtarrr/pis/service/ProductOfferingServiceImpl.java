package com.github.mtarrr.pis.service;

import com.github.mtarrr.pis.entity.ProductOfferingEntity;
import com.github.mtarrr.pis.repository.ProductOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOfferingServiceImpl implements ProductOfferingService {

    private final ProductOfferingRepository repository;

    @Override
    public ProductOfferingEntity createProductOffering(ProductOfferingEntity productOffering) {
        return null;
    }

    @Override
    public ProductOfferingEntity patchProductOffering(ProductOfferingEntity productOffering) {
        return null;
    }

    @Override
    public List<ProductOfferingEntity> getAllProductOfferings() {
        return null;
    }

    @Override
    public ProductOfferingEntity getProductOfferingById(String id) {
        return null;
    }

    @Override
    public void deleteProductOffering(String id) {

    }
}
