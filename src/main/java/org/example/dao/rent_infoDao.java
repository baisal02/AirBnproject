package org.example.dao;

import org.example.entities.rent_info;

import java.util.Date;
import java.util.List;

public interface rent_infoDao {
    public List<rent_info> query_rent_infoBetweenDates(Date startDate, Date endDate);
    public int getNumberOfRentedHousesByAgencyId(Long agencyId);
}
