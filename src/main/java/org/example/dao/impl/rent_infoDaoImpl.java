package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.example.config.HibernateConfig;
import org.example.dao.rent_infoDao;
import org.example.entities.Agency;
import org.example.entities.House;
import org.example.entities.Owner;
import org.example.entities.rent_info;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class rent_infoDaoImpl implements rent_infoDao {
    private final EntityManagerFactory factory= HibernateConfig.entityManagerFactory();
    @Override
    public List<rent_info> query_rent_infoBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<rent_info>returningList=new ArrayList<>();
        try(EntityManager em = factory.createEntityManager()){
            em.getTransaction().begin();
            List<rent_info>rentInfos = em.createQuery("FROM rent_info", rent_info.class).getResultList();
            for (rent_info rentInfo : rentInfos) {
                //converting Date into LocalDate, and checking if its between of the dates we give
                LocalDate checkOut = rentInfo.getCheckOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(checkOut.isAfter(startDate) && checkOut.isBefore(endDate)){
                    returningList.add(rentInfo);
                }
            }
            em.getTransaction().commit();
            return returningList;
        }catch (HibernateException h){
            System.out.println(h.getMessage());
        }
        return null;
    }

    @Override
    public int getNumberOfRentedHousesByAgencyId(Long agencyId) {
        int returningValue=0;
        List<House>houses = new ArrayList<>();
        try(EntityManager em = factory.createEntityManager()){
            em.getTransaction().begin();
            Agency agency = em.find(Agency.class, agencyId);
            for (Owner owner: agency.getOwners()) {
                for (House house : owner.getHouses()) {
                    //converting dates into LocalDate
                    if (house.getRent_info() != null) {
                        LocalDate checkIn = house.getRent_info().getCheckIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate checkOut = house.getRent_info().getCheckOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate localDate = LocalDate.now();
                        if (localDate.isAfter(checkIn) && localDate.isBefore(checkOut)) {
                            returningValue++;
                        }
                    }

                }
            }
            em.getTransaction().commit();
            return returningValue;
        }catch (HibernateException h){
            System.out.println(h.getMessage());
        }
        return 0;
    }
}
