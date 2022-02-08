package com.github.mtarrr.pis.service.repository;

import com.github.mtarrr.pis.db.jooq.Tables;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import org.jooq.Field;
import org.jooq.OrderField;
import org.jooq.Table;
import org.springframework.stereotype.Repository;

@Repository
public class ProductOfferingRepositoryImpl extends AbstractBaseRepository<String, ProductOfferingEntity> {

    @Override
    public Table<?> getTable() {
        return Tables.PRODUCT_OFFERING;
    }

    @Override
    public OrderField<?> getDefaultOrderBy() {
        return Tables.PRODUCT_OFFERING.LAST_UPDATE;
    }

    @Override
    public Class<ProductOfferingEntity> getEntityClass() {
        return ProductOfferingEntity.class;
    }

    @Override
    public Field<String> getIdField() {
        return Tables.PRODUCT_OFFERING.ID;
    }
}
