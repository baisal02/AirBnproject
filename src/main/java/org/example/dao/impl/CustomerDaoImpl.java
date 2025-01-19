package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.dao.CustomerDao;
import org.example.entities.*;
import org.hibernate.HibernateException;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private final EntityManagerFactory factory = HibernateConfig.entityManagerFactory();

    @Override
    public void save(Customer customer) {
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void RentHouse(Long CustomerId, Long HouseId, Date checkIn, Date checkOut) {
        rent_info rentinfo = new rent_info();
        rentinfo.setCheckIn(checkIn);
        rentinfo.setCheckOut(checkOut);

        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, CustomerId);
            rentinfo.setCustomer(customer);
            customer.getRentInfoList().add(rentinfo);
            em.persist(customer);

            //to make relation
            House house = em.find(House.class, HouseId);
            house.setRent_info(rentinfo);
            //to make relation
            Owner owner = em.find(Owner.class, house.getOwner().getId());
            owner.getRent_infos().add(rentinfo);
            rentinfo.setOwner(owner);

            em.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveWithRent(Customer customer, Long HouseId, Date checkIn, Date checkOut) {

        try (EntityManager em = factory.createEntityManager()) {
            rent_info rentinfo = new rent_info();
            rentinfo.setCheckIn(checkIn);
            rentinfo.setCheckOut(checkOut);
            rentinfo.setCustomer(customer);
            customer.getRentInfoList().add(rentinfo);
            em.getTransaction().begin();
            em.persist(customer);

            House house = em.find(House.class, HouseId);
            house.setRent_info(rentinfo);
            em.persist(house);
            //to have relations between owner and rentinfos
            if (house.getOwner() != null) {
                Owner owner = em.find(Owner.class, house.getOwner().getId());
                owner.getRent_infos().add(rentinfo);
                em.persist(owner);
            }
            //could make relation between agencies and the rentinfo
            // no point in that
            // can get access through owners
            em.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCustomer(Long CustomerId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try (EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, CustomerId);
            int a = customer.getRentInfoList().size();
            if (customer.getRentInfoList().isEmpty() ||
                    customer.getRentInfoList().get(a - 1).
                            getCheckOut().
                            toInstant().
                            atZone(ZoneId.systemDefault()).
                            toLocalDate().
                            isBefore(LocalDate.now())) {
                customer.getRentInfoList().clear();
                em.remove(customer);
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

}
