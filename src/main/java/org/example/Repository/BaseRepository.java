package org.example.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class BaseRepository <T> {
    protected EntityManager em;

    public BaseRepository(EntityManager em) {
        this.em = em;
    }

    public abstract void save (T element);
    public abstract void delete (T element);
    public abstract T findById (int id);
    public abstract List<T> findAll ();
}
