package org.example.Repository;

import org.example.Entity.Product;
import org.example.Entity.ProductFood;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductFoodRepository extends BaseRepository<ProductFood>{
    public ProductFoodRepository(EntityManager em) {
        super(em);
    }

    @Override
    public void save(ProductFood element) {
        em.getTransaction().begin();
        em.persist(element);
        em.getTransaction().commit();
    }

    @Override
    public void delete(ProductFood element) {
        em.getTransaction().begin();
        em.remove(element);
        em.getTransaction().commit();

    }

    @Override
    public ProductFood findById(int id) {
        return em.find(ProductFood.class, id);
    }

    @Override
    public List<ProductFood> findAll() {
        return  em.createQuery("SELECT c FROM ProductFood  c").getResultList();
    }


}
