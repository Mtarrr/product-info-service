package com.github.mtarrr.pis.service;

import com.github.mtarrr.pis.NotificationService;
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
    private final NotificationService notificationService;

    @Override
    public ProductOfferingEntity createProductOffering(ProductOfferingEntity productOffering) throws Exception {
        ProductOfferingEntity createdEntity = repository.insert(productOffering);
        elasticService.saveToElastic(createdEntity);
        notificationService.send(createdEntity);
        return createdEntity;
    }

    @Override
    public ProductOfferingEntity patchProductOffering(String id, ProductOfferingEntity patchProductOffering) throws Exception {
        ProductOfferingEntity storedEntity = getProductOfferingById(id);
        PatchUtils.patchProductOfferingEntity(storedEntity, patchProductOffering);
        ProductOfferingEntity patchedEntity = repository.update(id, storedEntity);
        elasticService.saveToElastic(patchedEntity);
        notificationService.send(patchedEntity);
        return patchedEntity;
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
        repository.delete(id);
        elasticService.deleteById(id);
    }
}
