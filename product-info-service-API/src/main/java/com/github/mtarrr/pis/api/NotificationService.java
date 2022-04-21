package com.github.mtarrr.pis.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;

public interface NotificationService {

    void send(ProductOfferingEntity entity) throws JsonProcessingException;
}
