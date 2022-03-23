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

@Service
public class ElasticService {
    @Autowired
    private RestHighLevelClient client;

    private ObjectMapper objectMapper;

    private final ProductOfferingMapper productOfferingMapper = ProductOfferingMapper.INSTANCE;

    private static final String INDEX_NAME = "product";
    private static final String INDEX_TYPE = "doc";

    @Autowired
    public ElasticService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public void saveToElastic(ProductOfferingEntity entity) throws Exception {
        ProductOffering productOffering = productOfferingMapper.map(entity);

        Map<String, Object> mapper = objectMapper.convertValue(productOffering, Map.class);

        IndexRequest indexRequest = new IndexRequest(INDEX_NAME, INDEX_TYPE, entity.getId())
                .source(mapper);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

    }

    public void deleteById(String id) throws Exception {

        DeleteRequest deleteRequest = new DeleteRequest(INDEX_NAME, INDEX_TYPE, id);
        DeleteResponse response =
                client.delete(deleteRequest, RequestOptions.DEFAULT);

    }

}
