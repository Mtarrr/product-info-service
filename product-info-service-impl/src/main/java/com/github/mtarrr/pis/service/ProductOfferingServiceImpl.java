package com.github.mtarrr.pis.service;

import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import com.github.mtarrr.pis.repository.ProductOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOfferingServiceImpl implements ProductOfferingService {

    private final ProductOfferingRepository repository;
    private final ElasticService elasticService;

    @Override
    public ProductOfferingEntity createProductOffering(ProductOfferingEntity productOffering) throws Exception {
        elasticService.saveToElastic(productOffering);
        return repository.insert(productOffering);
    }

    @Override
    public ProductOfferingEntity patchProductOffering(String id, ProductOfferingEntity patchProductOffering) throws Exception {
        ProductOfferingEntity storedEntity = getProductOfferingById(id);
        PatchUtils.patchProductOfferingEntity(storedEntity, patchProductOffering);
        elasticService.saveToElastic(storedEntity);
        return repository.update(id, storedEntity);
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
    public void deleteProductOffering(String id) throws Exception {
        elasticService.deleteById(id);
        repository.delete(id);
    }
}
