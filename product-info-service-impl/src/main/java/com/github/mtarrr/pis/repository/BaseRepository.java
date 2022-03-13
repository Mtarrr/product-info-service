package com.github.mtarrr.pis.repository;

import java.util.List;

public interface BaseRepository<K, E> {
    E insert(E entity);

    E get(K id);

    List<E> getAll();

    E update(K id, E entity);

    void delete(K id);
}
