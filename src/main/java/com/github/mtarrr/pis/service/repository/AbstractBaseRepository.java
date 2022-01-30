package com.github.mtarrr.pis.service.repository;

import org.jooq.DSLContext;
import org.jooq.OrderField;
import org.jooq.Record;
import org.jooq.Table;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public abstract class AbstractBaseRepository<K, E> implements BaseRepository<K, E> {

    protected DSLContext dsl;

    protected AbstractBaseRepository() throws SQLException {
    }

    public abstract Table<?> getTable();

    public abstract OrderField<?> getDefaultOrderBy();

    public abstract Class<E> getEntityClass();

    public abstract Record createRecordFromEntity(E entity);

    public E insert(E entity) {
        return dsl.insertInto(getTable()).set(createRecordFromEntity(entity)).returning().fetchOne().into(getEntityClass());
    }

    public E get(K id) {
        return null;
    }

    public List<E> getAll() {
        return dsl.selectFrom(getTable()).orderBy(getDefaultOrderBy()).fetch().into(getEntityClass());
    }

    public E update(E entity) {
        return null;
    }

    public void delete(K id) {

    }
}
