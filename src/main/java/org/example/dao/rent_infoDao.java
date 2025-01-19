package org.example.dao;

import org.example.entities.rent_info;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface rent_infoDao {
    public List<rent_info> query_rent_infoBetweenDates(LocalDate startDate, LocalDate endDate);
    public int getNumberOfRentedHousesByAgencyId(Long agencyId);
}
