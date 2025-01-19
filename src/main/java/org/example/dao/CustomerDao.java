package org.example.dao;

import org.example.entities.Customer;
import org.example.entities.rent_info;

import java.util.Date;

public interface CustomerDao {
  public void save(Customer customer);
  public void RentHouse(Long CustomerId, Long HouseId, Date checkIn, Date checkOut);
  public void saveWithRent(Customer customer,Long HouseId,Date checkIn,Date checkOut);
  public void deleteCustomer(Long CustomerId);
}
