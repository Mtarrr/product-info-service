package com.github.mtarrr.pis.mapper;

import com.github.mtarrr.pis.model.ProductOffering;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface ProductOfferingMapper {

    ProductOfferingMapper INSTANCE = Mappers.getMapper(ProductOfferingMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "lastUpdate", target = "lastUpdate")
    @Mapping(source = "version", target = "version")
    @Mapping(source = "body.name", target = "name")
    @Mapping(source = "body.description", target = "description")
    @Mapping(source = "body.extendedParameters", target = "extendedParameters")
    @Mapping(source = "body.href", target = "href")
    @Mapping(source = "body.productOfferingPrice", target = "productOfferingPrice")
    @Mapping(source = "body.category", target = "category")
    @Mapping(source = "body.channel", target = "channel")
    @Mapping(source = "body.customerCategory", target = "customerCategory")
    ProductOffering map(ProductOfferingEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "lastUpdate", source = "lastUpdate")
    @Mapping(target = "version", source = "version")
    @Mapping(target = "body.name", source = "name")
    @Mapping(target = "body.description", source = "description")
    @Mapping(target = "body.extendedParameters", source = "extendedParameters")
    @Mapping(target = "body.href", source = "href")
    @Mapping(target = "body.productOfferingPrice", source = "productOfferingPrice")
    @Mapping(target = "body.category", source = "category")
    @Mapping(target = "body.channel", source = "channel")
    @Mapping(target = "body.customerCategory", source = "customerCategory")
    ProductOfferingEntity map(ProductOffering productOffering);
}
