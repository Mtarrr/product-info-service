package com.github.mtarrr.pis.service.repository;

import org.jooq.Record;
import org.jooq.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class AbstractBaseRepository<K extends Serializable, E> implements BaseRepository<K, E> {

    protected DSLContext dsl;

    protected AbstractBaseRepository() {
    }

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
        return Objects.requireNonNull(dsl.insertInto(getTable())
                        .set(createRecordFromEntity(entity))
                        .returning()
                        .fetchOne())
                .into(getEntityClass());
    }

    public E get(K id) {
        return Objects.requireNonNull(dsl.selectFrom(getTable())
                        .where(isIdEqual(id))
                        .fetchOne())
                .into(getEntityClass());
    }

    public List<E> getAll() {
        return dsl.selectFrom(getTable())
                .orderBy(getDefaultOrderBy())
                .fetch()
                .into(getEntityClass());
    }

    public E update(K id, E entity) {
        return dsl.update(getTable())
                .set(createRecordFromEntity(entity))
                .where(isIdEqual(id))
                .returning()
                .fetchOne()
                .into(getEntityClass());
    }

    public void delete(K id) {
        dsl.delete(getTable())
                .where(isIdEqual(id));
    }
}
