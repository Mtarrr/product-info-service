package com.github.mtarrr.pis.repository;

import com.github.mtarrr.pis.entity.ProductOfferingEntity;
import org.jooq.OrderField;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.codegen.maven.example.Tables;

import java.sql.SQLException;

public class ProductOfferingRepositoryImpl extends AbstractBaseRepository<String, ProductOfferingEntity> {
    protected ProductOfferingRepositoryImpl() throws SQLException {
    }

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
        return null;
    }

    @Override
    public Record createRecordFromEntity(ProductOfferingEntity entity) {
        return null;
    }
}
