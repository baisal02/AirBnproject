package org.example;

import org.example.config.HibernateConfig;
import org.example.dao.AgencyDao;
import org.example.dao.CustomerDao;
import org.example.dao.HouseDao;
import org.example.dao.OwnerDao;
import org.example.dao.impl.AgencyDaoImpl;
import org.example.dao.impl.CustomerDaoImpl;
import org.example.dao.impl.HouseDaoImpl;
import org.example.dao.impl.OwnerDaoImpl;
import org.example.entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException {
        AgencyDao agencyDao = new AgencyDaoImpl();
        Agency agency = new Agency();
        agency.setName("kurulush");
        Address address1 = new Address();
        address1.setStreet("chui_111ff");
       // agencyDao.save(agency,address1);
       // agencyDao.save(agency, address);
        CustomerDao customerDao = new CustomerDaoImpl();

        HouseDao houseDao = new HouseDaoImpl();
        Customer customer = new Customer();
        customer.setFirstName("baisal");
        customer.setEmail("baisal@gmail.com");
        customer.setLastName("altynbekuulu");
        House house = new House();
        house.setDescription("Star");
       // houseDao.save(house);

        //customerDao.save(customer);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Customer customer4 = new Customer();
            customer4.setFirstName("Ernazar");
            customer4.setEmail("ernazar@gmail.com");
            OwnerDao ownerDao = new OwnerDaoImpl();

        Owner owner = new Owner();
        owner.setFirstName("Joomart");
        owner.setEmail("Joma@gmail.com");
        House houseA = new House();
        houseA.setDescription("CrazyHouse");
       // ownerDao.save(owner,houseA);

        customerDao.saveWithRent(customer4,102L,format.parse("2025-12-20"),format.parse("2025-12-30"));
    }

}
