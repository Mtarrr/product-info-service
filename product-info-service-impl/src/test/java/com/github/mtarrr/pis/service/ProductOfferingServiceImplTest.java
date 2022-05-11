package com.github.mtarrr.pis.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mtarrr.pis.mapper.ProductOfferingMapper;
import com.github.mtarrr.pis.model.ProductOffering;
import com.github.mtarrr.pis.model.entity.ProductOfferingBody;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import com.github.mtarrr.pis.repository.ProductOfferingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductOfferingServiceImplTest {

    @Mock
    private ProductOfferingRepository repository;
    @Mock
    private ElasticServiceImpl elasticService;
    @Mock
    private NotificationServiceImpl notificationService;
    @InjectMocks
    private ProductOfferingServiceImpl productOfferingService;

    private final ProductOfferingMapper productOfferingMapper = Mappers.getMapper(ProductOfferingMapper.class);

    private final ObjectMapper mapper = new ObjectMapper(new JsonFactory());

    private final InputStream stream = ProductOfferingServiceImplTest.class.getResourceAsStream("/TestEntity.json");
    private ProductOffering testProduct;

    {
        try {
            testProduct = mapper.readValue(stream, ProductOffering.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final ProductOfferingEntity testEntity = productOfferingMapper.map(this.testProduct);

    @Test
    void canCreateProductOffering() throws Exception {

        Mockito.when(repository.insert(Mockito.any())).thenAnswer(args -> {
            ProductOfferingEntity entity = args.getArgument(0, ProductOfferingEntity.class);
            entity.setVersion(2L);
            return entity;
        });
        Assertions.assertThat(testEntity.getVersion() == 1).isTrue();

        ProductOfferingEntity resultEntity = productOfferingService.createProductOffering(testEntity);

        Assertions.assertThat(resultEntity.getVersion() == 2L).isTrue();
    }

    @Test
    void canPatchProductOffering() throws Exception {

        Mockito.when(repository.get(Mockito.anyString())).thenAnswer(args -> testEntity);

        Mockito.when(repository.update(Mockito.anyString(), Mockito.any())).thenAnswer(args -> {
            ProductOfferingEntity entity1 = args.getArgument(1, ProductOfferingEntity.class);
            ProductOfferingBody body = new ProductOfferingBody();
            body.setName("Harry Potter2");
            body.setHref("book");
            entity1.setBody(body);
            entity1.setVersion(2L);
            return entity1;
        });

        Assertions.assertThat(testEntity.getVersion() == 1L).isTrue();
        Assertions.assertThat(Objects.equals(testEntity.getBody().getHref(), "href")).isTrue();
        Assertions.assertThat(Objects.equals(testEntity.getBody().getName(), "Harry Potter")).isTrue();

        ProductOfferingEntity resultEntity = productOfferingService.patchProductOffering(testEntity.getId(), testEntity);

        Assertions.assertThat(resultEntity.getVersion() == 2L).isTrue();
        Assertions.assertThat(Objects.equals(testEntity.getBody().getHref(), "book")).isTrue();
        Assertions.assertThat(Objects.equals(testEntity.getBody().getName(), "Harry Potter2")).isTrue();
    }

    @Test
    void canGetAllProductOfferings() {
        productOfferingService.getAllProductOfferings();
        verify(repository).getAll();
    }

    @Test
    void canGetProductOfferingById() {
        productOfferingService.getProductOfferingById(anyString());
        verify(repository).get(anyString());
    }

    @Test
    void canDeleteProductOffering() throws Exception {
        productOfferingService.deleteProductOffering(anyString());
        verify(repository).delete(anyString());
        verify(elasticService).deleteById(anyString());
    }
}