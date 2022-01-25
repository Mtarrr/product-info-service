package com.github.mtarrr.pis.entity.binding;

import com.github.mtarrr.pis.entity.ProductOfferingBody;
import org.jetbrains.annotations.NotNull;
import org.jooq.Converter;

public class JSONBProductOfferingBodyConverter implements Converter<String, ProductOfferingBody> {
    @Override
    public ProductOfferingBody from(String databaseObject) {
        return null;
    }

    @Override
    public String to(ProductOfferingBody userObject) {
        return null;
    }

    @Override
    public @NotNull Class<String> fromType() {
        return null;
    }

    @Override
    public @NotNull Class<ProductOfferingBody> toType() {
        return null;
    }
}
