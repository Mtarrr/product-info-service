package com.github.mtarrr.pis.service;

import com.github.mtarrr.pis.api.service.ElasticService;
import com.github.mtarrr.pis.api.service.NotificationService;
import com.github.mtarrr.pis.api.service.ProductOfferingService;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import com.github.mtarrr.pis.repository.ProductOfferingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductOfferingServiceImpl implements ProductOfferingService {

    private final ProductOfferingRepository repository;
    private final ElasticService elasticService;
    private final NotificationService notificationService;

    @Override
    public ProductOfferingEntity createProductOffering(ProductOfferingEntity productOffering) throws Exception {
        log.debug("Creating entity");
        ProductOfferingEntity createdEntity = repository.insert(productOffering);
        elasticService.saveToElastic(createdEntity);
        notificationService.send(createdEntity);
        log.debug("Entity {} created", createdEntity.getBody().getName());
        return createdEntity;
    }

    @Override
    public ProductOfferingEntity patchProductOffering(String id, ProductOfferingEntity patchProductOffering) throws Exception {
        log.debug("Patching entity");
        ProductOfferingEntity storedEntity = getProductOfferingById(id);
        PatchUtils.patchProductOfferingEntity(storedEntity, patchProductOffering);
        ProductOfferingEntity patchedEntity = repository.update(id, storedEntity);
        elasticService.saveToElastic(patchedEntity);
        notificationService.send(patchedEntity);
        log.debug("Entity {} got new fields", patchedEntity.getBody().getName());
        return patchedEntity;
    }

    @Override
    public List<ProductOfferingEntity> getAllProductOfferings() {
        log.debug("Getting all entities");
        return repository.getAll();
    }

    @Override
    public ProductOfferingEntity getProductOfferingById(String id)  {
        log.debug("Getting entity with id {}", id);
        ProductOfferingEntity entity = null;
        try {
            entity = repository.get(id);
        } catch (NullPointerException e) {
            log.warn("Entity with id {} doesn't exist", id);
        }
        return entity;
    }

    @Override
    public void deleteProductOffering(String id) throws Exception {
        try {
            repository.delete(id);
            elasticService.deleteById(id);
            log.info("Entity with id {} deleted", id);
        } catch (NullPointerException e) {
            log.warn("Entity with id {} doesn't exist", id);
        }
    }
}
