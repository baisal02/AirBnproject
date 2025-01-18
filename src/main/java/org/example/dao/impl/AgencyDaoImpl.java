package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.dao.AgencyDao;
import org.example.entities.Address;
import org.example.entities.Agency;
import org.hibernate.HibernateException;

public class AgencyDaoImpl implements AgencyDao {
    private final EntityManagerFactory factory = HibernateConfig.entityManagerFactory();
    @Override
    public String save(Agency agency, Address address) {
        agency.setAddress(address);
        address.setAgency(agency);
        try(EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(agency);
            em.getTransaction().commit();
        }catch (HibernateException e)
        {
            System.out.println(e.getMessage());
        }
        return "";
    }

    @Override
    public Agency findAgencyByID(Long id) {
        try(EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Agency agency = em.find(Agency.class, id);
            em.getTransaction().commit();
            return agency;
        }catch (HibernateException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public String updateAgency(Agency agency,Long id) {
        try(EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Agency agencyDB =em.find(Agency.class, agency.getId());
            agencyDB.setAddress(agency.getAddress());
            agencyDB.setName(agency.getName());
            agencyDB.setPhoneNumber(agency.getPhoneNumber());
            agencyDB.setOwners(agency.getOwners());
            agencyDB.setRent_infos(agency.getRent_infos());
            em.merge(agencyDB);
            em.getTransaction().commit();
            return "succeed";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return "failed";
    }

    @Override
    public String removeAgency(Long id) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Agency agency = em.find(Agency.class, id);
            if(agency!=null){
                em.remove(agency);
                return "succeed";
            }
            em.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return "failed";
    }


}
