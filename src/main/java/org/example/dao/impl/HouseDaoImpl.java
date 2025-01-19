package org.example.dao.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.dao.HouseDao;
import org.example.entities.Agency;
import org.example.entities.House;
import org.example.entities.Owner;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HouseDaoImpl implements HouseDao {
    private final EntityManagerFactory factory = HibernateConfig.entityManagerFactory();

    @Override
    public void save(House house) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(house);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveHouseRelatedToOwner(House house, Long ownerId) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Owner owner = em.find(Owner.class, ownerId);
            owner.getHouses().add(house);
            house.setOwner(owner);
            em.persist(house);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<House> getHousesByRegion(String regionName) {
        List<House> houses = new ArrayList<>();
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            for (House h : em.createQuery("FROM House", House.class).getResultList()) {
                if (h.getAddress().getRegion().equalsIgnoreCase(regionName)) {
                    houses.add(h);
                }
            }
            em.getTransaction().commit();
            return houses;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<House> getHousesByAgencyId(Long agencyId) {
        List<House> houses = new ArrayList<>();
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Agency agency = em.find(Agency.class, agencyId);
            for (Owner o : agency.getOwners()) {
                o.getHouses().forEach(house -> houses.add(house));
            }
            em.getTransaction().commit();
            return houses;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<House> getHousesByOwnerId(Long ownerId) {
        List<House> houses = new ArrayList<>();
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Owner owner = em.find(Owner.class, ownerId);
            if (owner != null && owner.getHouses() != null) {
                return owner.getHouses();
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<House> findBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<House> returningHouses = new ArrayList<>();

        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            List<House> houses = em.createQuery("FROM House", House.class).getResultList();
            for (House theHouse : houses) {
                //from Date to LocalDate
                LocalDate ThehouseCheckIn = theHouse.getRent_info().getCheckIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (ThehouseCheckIn.isAfter(startDate) && ThehouseCheckIn.isBefore(endDate)) {
                    returningHouses.add(theHouse);
                }
            }
            em.getTransaction().commit();
            return returningHouses;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteHouseById(Long houseId) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            House house = em.find(House.class, houseId);
            if (house.getOwner() != null &&
                    house.getRent_info() == null ||
                    house.getRent_info().getCheckOut().
                            toInstant().
                            atZone(ZoneId.systemDefault()).
                            toLocalDate().
                            isBefore(LocalDate.now())) {
                house.getOwner().getHouses().remove(house);
                em.remove(house);
            }
            em.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

}
