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
    ProductOffering map(ProductOfferingEntity entity);

    @Mapping(source = "id", target = "id")
    ProductOfferingEntity map(ProductOffering productOffering);


}
