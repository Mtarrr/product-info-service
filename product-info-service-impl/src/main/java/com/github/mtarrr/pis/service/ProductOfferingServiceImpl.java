package com.github.mtarrr.pis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.mtarrr.pis.api.service.ElasticService;
import com.github.mtarrr.pis.api.service.NotificationService;
import com.github.mtarrr.pis.api.service.ProductOfferingService;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import com.github.mtarrr.pis.repository.ProductOfferingRepository;
import com.github.mtarrr.pis.utils.ElasticServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductOfferingServiceImpl implements ProductOfferingService {

    private final ProductOfferingRepository repository;
    private final ElasticService elasticService;
    private final NotificationService notificationService;

    @Override
    public ProductOfferingEntity createProductOffering(ProductOfferingEntity productOffering) {
        log.debug("Creating entity");
        ProductOfferingEntity createdEntity = repository.insert(productOffering);
        exceptionHandling(createdEntity);
        log.debug("Entity {} created", createdEntity.getBody().getName());
        return createdEntity;
    }

    @Override
    public ProductOfferingEntity patchProductOffering(String id, ProductOfferingEntity patchProductOffering) {
        log.debug("Patching entity");
        ProductOfferingEntity storedEntity = getProductOfferingById(id);
        PatchUtils.patchProductOfferingEntity(storedEntity, patchProductOffering);
        ProductOfferingEntity patchedEntity = repository.update(id, storedEntity);
        exceptionHandling(patchedEntity);
        log.debug("Entity {} got new fields", patchedEntity.getBody().getName());
        return patchedEntity;
    }

    private void exceptionHandling(ProductOfferingEntity patchedEntity) {
        try {
            elasticService.saveToElastic(patchedEntity);
        } catch (IOException e) {
            log.debug("IOException in saveToElastic method");
            throw new ElasticServiceException(e.toString(), "Save to Elastic problem");
        }
        try {
            notificationService.send(patchedEntity);
        } catch (JsonProcessingException e) {
            log.debug("JsonProcessingException in send method");
            throw new ElasticServiceException(e.toString(), "Send method problem");
        }
    }

    @Override
    public List<ProductOfferingEntity> getAllProductOfferings() {
        log.debug("Getting all entities");
        return repository.getAll();
    }

    @Override
    public ProductOfferingEntity getProductOfferingById(String id) {
        log.debug("Getting entity with id {}", id);
        return repository.get(id);
    }

    @Override
    public void deleteProductOffering(String id) {
        repository.delete(id);
        try {
            elasticService.deleteById(id);
            log.info("Entity with id {} deleted", id);
        } catch (IOException e) {
            throw new ElasticServiceException(e.toString(), "Delete from Elastic problem");
        }

    }
}
