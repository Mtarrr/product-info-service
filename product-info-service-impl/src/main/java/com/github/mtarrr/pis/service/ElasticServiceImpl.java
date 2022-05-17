package com.github.mtarrr.pis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mtarrr.pis.api.service.ElasticService;
import com.github.mtarrr.pis.mapper.ProductOfferingMapper;
import com.github.mtarrr.pis.model.ProductOffering;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import lombok.Data;
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
@Data
public class ElasticServiceImpl implements ElasticService {
    @Autowired
    private final RestHighLevelClient client;

    private final ObjectMapper objectMapper;

    private final ProductOfferingMapper productOfferingMapper = ProductOfferingMapper.INSTANCE;

    private static final String INDEX_NAME = "product";
    private static final String INDEX_TYPE = "_doc";

    public void saveToElastic(ProductOfferingEntity entity) throws Exception {
        ProductOffering productOffering = productOfferingMapper.map(entity);

        Map<String, Object> mapper = objectMapper.convertValue(productOffering, Map.class);

        IndexRequest indexRequest = new IndexRequest(INDEX_NAME, INDEX_TYPE, entity.getId())
                .source(mapper);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

    }

    public void deleteById(String id) throws Exception {

        DeleteRequest deleteRequest = new DeleteRequest(INDEX_NAME, INDEX_TYPE, id);
        //log trace отладочная информация
        DeleteResponse response =
                client.delete(deleteRequest, RequestOptions.DEFAULT);

    }

}
