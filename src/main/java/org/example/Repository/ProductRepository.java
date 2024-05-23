package org.example.Repository;

import org.example.Entity.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductRepository extends BaseRepository<Product>{

    public ProductRepository(EntityManager em) {
        super(em);
    }

    @Override
    public void save(Product element) {
        em.getTransaction().begin();
        em.persist(element);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Product element) {
        em.getTransaction().begin();
        em.remove(element);
        em.getTransaction().commit();
    }

    @Override
    public Product findById(int id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("SELECT c FROM Product  c").getResultList();

    }
}
