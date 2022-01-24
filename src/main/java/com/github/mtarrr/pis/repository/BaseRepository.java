package com.github.mtarrr.pis.repository;

import java.util.List;

public interface BaseRepository<K, E> {
    public E insert(E entity);
    public E get(K id);
    public List<E> getAll();
    public E update(E entity);
    public void delete(K id);
}
