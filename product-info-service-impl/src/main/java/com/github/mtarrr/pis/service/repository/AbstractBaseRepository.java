package com.github.mtarrr.pis.service.repository;

import org.jooq.Record;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractBaseRepository<K extends Serializable, E> implements BaseRepository<K, E> {

    @Autowired
    protected DSLContext dsl;

    public abstract Table<?> getTable();

    public abstract OrderField<?> getDefaultOrderBy();

    public abstract Class<E> getEntityClass();

    public abstract Field<K> getIdField();

    private Condition isIdEqual(K id) {
        return getIdField().eq(id);
    }

    protected Record createRecordFromEntity(E entity) {
        UpdatableRecord<?> newRecord = dsl.<UpdatableRecord<?>>newRecord((Table) getTable());
        newRecord.from(entity);

        return newRecord;
    }

    public E insert(E entity) {
        Record newRecord = createRecordFromEntity(entity);
        processBeforeInsert(newRecord);

        return dsl.insertInto(getTable())
                .set(createRecordFromEntity(entity))
                .set(newRecord)
                .returning()
                .fetchOne()
                .into(getEntityClass());

    }

    public E get(K id) {
        return dsl.selectFrom(getTable())
                .where(isIdEqual(id))
                .fetchOne()
                .into(getEntityClass());
    }

    public List<E> getAll() {
        return dsl.selectFrom(getTable())
                .orderBy(getDefaultOrderBy())
                .fetch()
                .into(getEntityClass());
    }

    public E update(K id, E entity) {
        Record updatedRecord = createRecordFromEntity(entity);
        processBeforeUpdate(id, updatedRecord);

        return dsl.update(getTable())
                .set(updatedRecord)
                .where(isIdEqual(id))
                .returning()
                .fetchOne()
                .into(getEntityClass());

    }

    public void delete(K id) {
        dsl.delete(getTable())
                .where(isIdEqual(id));
    }

    protected abstract void processBeforeInsert(Record newRecord);

    protected abstract void processBeforeUpdate(K id, Record updatedRecord);
}
