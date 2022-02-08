package com.github.mtarrr.pis.model.entity.binding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mtarrr.pis.model.entity.ProductOfferingBody;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jooq.Converter;

@RequiredArgsConstructor
public class JSONBProductOfferingBodyConverter implements Converter<String, ProductOfferingBody> {

    private final ObjectMapper mapper;

    @Override
    @SneakyThrows
    public ProductOfferingBody from(String databaseObject) {
        if (databaseObject == null){
            return null;
        }
        return mapper.readValue(databaseObject, ProductOfferingBody.class);
    }


    @Override
    @SneakyThrows
    public String to(ProductOfferingBody userObject) {
        if (userObject == null){
            return null;
        }
        return mapper.writeValueAsString(userObject);
    }

    @Override
    public @NotNull Class<String> fromType() {
        return String.class;
    }

    @Override
    public @NotNull Class<ProductOfferingBody> toType() {
        return ProductOfferingBody.class;
    }
}
