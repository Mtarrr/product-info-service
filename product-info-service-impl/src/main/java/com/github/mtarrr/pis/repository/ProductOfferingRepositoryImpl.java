package com.github.mtarrr.pis.repository;

import com.github.mtarrr.pis.db.jooq.Tables;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;
import org.jooq.Field;
import org.jooq.OrderField;
import org.jooq.Record;
import org.jooq.Table;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public class ProductOfferingRepositoryImpl extends AbstractBaseRepository<String, ProductOfferingEntity> implements ProductOfferingRepository {

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

    @Override
    protected void processBeforeInsert(Record newRecord) {
        newRecord.set(this.getIdField(), UUID.randomUUID().toString());
        newRecord.set(Tables.PRODUCT_OFFERING.VERSION, 1L);
        newRecord.set(Tables.PRODUCT_OFFERING.LAST_UPDATE, OffsetDateTime.now());
    }

    @Override
    protected void processBeforeUpdate(String id, Record updatedRecord) {
        updatedRecord.set(this.getIdField(), id);

        Field versionField = Tables.PRODUCT_OFFERING.VERSION;
        updatedRecord.set(versionField, versionField.add(1));
        updatedRecord.set(Tables.PRODUCT_OFFERING.LAST_UPDATE, OffsetDateTime.now());
    }
}
