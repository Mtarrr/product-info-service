package com.github.mtarrr.pis.entity.binding;

import com.github.mtarrr.pis.entity.ProductOfferingBody;
import org.jetbrains.annotations.NotNull;
import org.jooq.Converter;
import org.jooq.JSONB;

public class JSONBProductOfferingBodyConverter implements Converter<JSONB, ProductOfferingBody> {
    @Override
    public ProductOfferingBody from(JSONB databaseObject) {
        return null;
    }

    @Override
    public JSONB to(ProductOfferingBody userObject) {
        return null;
    }

    @Override
    public @NotNull Class<JSONB> fromType() {
        return null;
    }

    @Override
    public @NotNull Class<ProductOfferingBody> toType() {
        return null;
    }
}
