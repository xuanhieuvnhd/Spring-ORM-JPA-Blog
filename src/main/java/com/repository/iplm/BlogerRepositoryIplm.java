package com.repository.iplm;

import com.model.Bloger;
import com.repository.BlogerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BlogerRepositoryIplm implements BlogerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Bloger> findAll() {
        TypedQuery<Bloger> query = em.createQuery("select c from Bloger c", Bloger.class);
        return query.getResultList();
    }

    @Override
    public Bloger findById(long id) {
        TypedQuery<Bloger> query = em.createQuery("select c from Bloger c where c.id=:id", Bloger.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Bloger model) {
        if (model.getId() != null) {
            em.merge(model);
        } else {
            em.persist(model);
        }
    }

    @Override
    public void remove(long id) {
        Bloger bloger = findById(id);
        if (bloger != null) {
            em.remove(bloger);
        }
    }
}
