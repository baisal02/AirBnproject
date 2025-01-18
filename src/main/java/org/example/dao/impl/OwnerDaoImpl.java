package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.dao.OwnerDao;
import org.example.entities.Agency;
import org.example.entities.House;
import org.example.entities.Owner;
import org.hibernate.HibernateException;
import org.hibernate.boot.models.categorize.spi.EntityHierarchy;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    private final EntityManagerFactory factory = HibernateConfig.entityManagerFactory();

    @Override
    public void saveOwnerWithHouse(Owner owner, House house) {
        if (Period.between(owner.getDateOfBirth().toInstant().
                        atZone(ZoneId.systemDefault()).
                        toLocalDate(), LocalDate.now()).
                getYears() > 18) {
            owner.getHouses().add(house);
            house.setOwner(owner);
            try (EntityManager em = factory.createEntityManager()) {
                em.getTransaction().begin();
                em.persist(owner);
                em.getTransaction().commit();
            } catch (HibernateException s) {
                System.out.println(s.getMessage());
            }
        }
    }

    @Override
    public void saveOwner(Owner owner) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(owner);
            em.getTransaction().commit();
        } catch (HibernateException s) {
            System.out.println(s.getMessage());
        }
    }

    @Override
    public void deleteOwner(Long ownerId) {
        int helper = 0;
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Owner owner = em.find(Owner.class, ownerId);
            List<House> houses = owner.getHouses();
            for (House house : houses) {
                if (house.getRent_info() == null || house.getRent_info().
                        getCheckOut().
                        toInstant().
                        atZone(ZoneId.systemDefault()).
                        toLocalDate().
                        isBefore(LocalDate.now())) {
                    helper++;
                }
            }
            if (helper == owner.getHouses().size()) {
                em.remove(owner);
            }
            em.getTransaction().commit();
        } catch (HibernateException s) {
            System.out.println(s.getMessage());
        }
    }

    @Override
    public void assignOwnerToAgency(Long ownerId, Long agencyId) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Agency agency = em.find(Agency.class, agencyId);
            Owner owner = em.find(Owner.class, ownerId);
            agency.getOwners().add(owner);
            owner.getAgencies().add(agency);
            // no need in persisting agency, because they are already in database
            em.getTransaction().commit();
        }catch (HibernateException s) {
            System.out.println(s.getMessage());
        }

    }

    @Override
    public List<Owner> getOwnersByAgency(Long agencyId) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Agency agency = em.find(Agency.class, agencyId);
            List<Owner> owners = agency.getOwners();
            em.getTransaction().commit();
            return owners;
        }catch (HibernateException s) {
            System.out.println(s.getMessage());
        }
        return null;
    }

    @Override
    public void getOwnersNameAge() {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            List<Owner> owners = em.createQuery("FROM Owner o", Owner.class).getResultList();
            for(Owner o :owners){
                System.out.println("name:"+o.getFirstName()+"   age:"+
                        Period.between(o.getDateOfBirth().toInstant().
                                atZone(ZoneId.systemDefault()).
                                toLocalDate(), LocalDate.now()));
            }
            em.getTransaction().commit();

        }catch (HibernateException s) {
            System.out.println(s.getMessage());
        }

    }


}


