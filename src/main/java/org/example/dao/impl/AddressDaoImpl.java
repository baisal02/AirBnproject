package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.example.config.HibernateConfig;
import org.example.dao.AddressDao;
import org.example.entities.Address;
import org.example.entities.Agency;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressDaoImpl implements AddressDao {
    private final EntityManagerFactory factory = HibernateConfig.entityManagerFactory();
    @Override
    public Map<Address, Agency> getAddress_Agencies() {
        List<Agency> agencies = new ArrayList<>();
        Map<Address, Agency> map = new HashMap<>();
        try(EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            agencies = em.createQuery("FROM Agency", Agency.class).getResultList();

            for (Agency ag : agencies) {
               map.put(em.find(Address.class,ag.getAddress().getId()),ag);
            }
            em.getTransaction().commit();
            return map;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int numberOfAgenciesInCity(String cityName) {
        List<Agency>agencies = new ArrayList<>();
        int count = 0;
        try(EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
           agencies = em.createQuery("from Agency", Agency.class).getResultList();
           for (Agency ag : agencies) {
               if(ag.getAddress().getCity().equals(cityName)){ count++;}
           }
           em.getTransaction().commit();
           return count;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        Map<String, List<Agency>> returnMap = new HashMap<>();
        List<Agency> temporaryAgencies = new ArrayList<>();

        try(EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            String hql = "SELECT a.region FROM Address a GROUP BY a.region";
            List<Agency>agencies = em.createQuery("FROM Agency", Agency.class).getResultList();
            List<String> regions = em.createQuery(hql).getResultList();
            for (String region : regions) {
                for (Agency agency:agencies){
                    if(agency.getAddress().getRegion().equals(region)){
                        temporaryAgencies.add(agency);
                    }
                    returnMap.put(region,temporaryAgencies);
                    temporaryAgencies.clear();
                }
            }
            return returnMap;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
