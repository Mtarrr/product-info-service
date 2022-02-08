package com.github.mtarrr.pis.service;

import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import com.github.mtarrr.pis.service.repository.ProductOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOfferingServiceImpl implements ProductOfferingService {

    private final ProductOfferingRepository repository;

    @Override
    public ProductOfferingEntity createProductOffering(ProductOfferingEntity productOffering) {
        return repository.insert(productOffering);
    }

    @Override
    public ProductOfferingEntity patchProductOffering(String id, ProductOfferingEntity productOffering) {
        ProductOfferingEntity entity = getProductOfferingById(id);
        entity.setBody(productOffering.getBody());
        entity.setVersion(productOffering.getVersion());
        entity.setLastUpdate(productOffering.getLastUpdate());
        entity.setId(productOffering.getId());
        return repository.update(productOffering.getId(), productOffering);
    }

    @Override
    public List<ProductOfferingEntity> getAllProductOfferings() {
        return repository.getAll();
    }

    @Override
    public ProductOfferingEntity getProductOfferingById(String id) {
        return repository.get(id);
    }

    @Override
    public void deleteProductOffering(String id) {
        repository.delete(id);
    }
}
