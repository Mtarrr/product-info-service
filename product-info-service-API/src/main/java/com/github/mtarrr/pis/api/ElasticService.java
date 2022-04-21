package com.github.mtarrr.pis.api;

import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;

public interface ElasticService {

    void saveToElastic(ProductOfferingEntity entity) throws Exception;

    void deleteById(String id) throws Exception;
}
