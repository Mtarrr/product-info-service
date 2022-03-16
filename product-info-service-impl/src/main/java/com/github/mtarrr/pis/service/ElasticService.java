package com.github.mtarrr.pis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mtarrr.pis.mapper.ProductOfferingMapper;
import com.github.mtarrr.pis.model.ProductOffering;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static javax.xml.crypto.dsig.SignatureProperties.TYPE;
import static org.elasticsearch.action.update.UpdateHelper.ContextFields.INDEX;

@Service
public class ElasticService {

    private RestHighLevelClient client;

    private ObjectMapper objectMapper;

    private final ProductOfferingMapper productOfferingMapper = ProductOfferingMapper.INSTANCE;

    @Autowired
    public ElasticService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public void saveToElastic(ProductOfferingEntity entity) throws Exception {
        ProductOffering productOffering = productOfferingMapper.map(entity);

        Map<String, Object> mapper = objectMapper.convertValue(productOffering, Map.class);

        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, entity.getId())
                .source(mapper);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

    }

    public void deleteById(String id) throws Exception {

        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
        DeleteResponse response =
                client.delete(deleteRequest, RequestOptions.DEFAULT);

    }

}
