package com.github.mtarrr.pis.mapper;

import com.github.mtarrr.pis.entity.ProductOfferingEntity;
import com.github.mtarrr.pis.model.ProductOffering;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper()
public interface ProductOfferingMapper {

    @Mapping(source = "id", target = "id")
    ProductOffering map(ProductOfferingEntity entity);

    @Mapping(source = "id", target = "id")
    ProductOfferingEntity map(ProductOffering productOffering);


} // source from target to
