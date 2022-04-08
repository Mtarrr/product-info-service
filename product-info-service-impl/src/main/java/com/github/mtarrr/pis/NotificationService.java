package com.github.mtarrr.pis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mtarrr.pis.mapper.ProductOfferingMapper;
import com.github.mtarrr.pis.model.ProductOffering;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Data
public class NotificationService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;
    private final ProductOfferingMapper productOfferingMapper = ProductOfferingMapper.INSTANCE;

    private static final String kafkaTopic = "product-offering-notification";

    public void send(ProductOfferingEntity entity) throws JsonProcessingException {
        ProductOffering productOffering = productOfferingMapper.map(entity);

        String message = objectMapper.writeValueAsString(productOffering);

        kafkaTemplate.send(kafkaTopic, message);
    }
}
