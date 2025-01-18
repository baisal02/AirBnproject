package org.example.dao.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.dao.HouseDao;
import org.example.entities.House;
import org.hibernate.HibernateException;

public class HouseDaoImpl implements HouseDao {
    private final EntityManagerFactory factory = HibernateConfig.entityManagerFactory();
    @Override
    public void save(House house) {
        try (EntityManager em = factory.createEntityManager()){
            em.getTransaction().begin();
            em.persist(house);
            em.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }
}
