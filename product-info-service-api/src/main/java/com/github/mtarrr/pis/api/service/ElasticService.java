package com.github.mtarrr.pis.api.service;

import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;

import java.io.IOException;

public interface ElasticService {

    void saveToElastic(ProductOfferingEntity entity) throws IOException;

    void deleteById(String id) throws IOException;
}
