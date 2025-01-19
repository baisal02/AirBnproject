package org.example;

import org.example.config.HibernateConfig;
import org.example.dao.*;
import org.example.dao.impl.*;
import org.example.entities.*;
import org.example.entities.enums.HouseType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        AddressDao addressDao = new AddressDaoImpl();
       Address addresse = new Address();
       addresse.setCity("Berlin");
       addresse.setStreet("Berlin123");
       addresse.setRegion("Germany");

       Agency agency1 = new Agency();
       agency1.setName("Peaksoft");
       agency1.setPhoneNumber("+996 13252532");


      //  System.out.println(addressDao.numberOfAgenciesInCity("Berlin"));
       Map<String, List<Agency>> map = addressDao.groupByRegion();

       House house1 = new House();
       house1.setDescription("Mercury");
       Address addressHouse = new Address();
       addressHouse.setCity("Bishkek");
       addressHouse.setStreet("jibekJOlu123");
       addressHouse.setRegion("KR");
       house1.setAddress(addressHouse);
       house1.setRoom(10);

       //houseDao.saveHouseRelatedToOwner(house1,1L);
        Customer newcustomer = new Customer();
        newcustomer.setFirstName("JanyCustomer");
        newcustomer.setEmail("jany@gmail.com");
        newcustomer.setLastName("Eskiev");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("20/11/2024");
        Date date2 = dateFormat.parse("25/12/2024");
       // customerDao.RentHouse(351L,201L,date,date2);
       //ownerDao.assignOwnerToAgency(1L,2L);

        //List<House>hh = houseDao.getHousesByOwnerId(1l);
        //System.out.println(hh.get(0).getDescription());

        customerDao.deleteCustomer(351L);

        Customer cust = new Customer();
        cust.setFirstName("Jack");
        cust.setEmail("jandde@gmail.com");

        House housew = new House();
        housew.setDescription("Pluton");
        // houseDao.saveHouseRelatedToOwner(housew,3L);

        Date dat = dateFormat.parse("10/10/2022");
        Date dat2 = dateFormat.parse("10/12/2023");
        //customerDao.saveWithRent(customer4,102L,format.parse("2025-12-20"),format.parse("2025-12-30"));
    }

}
