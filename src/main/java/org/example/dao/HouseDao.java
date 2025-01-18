package org.example.dao;

import org.example.entities.House;

import java.util.Date;
import java.util.List;

public interface HouseDao {
    public void save(House house);
    public void saceHouseRelatedToOwner(House house,Long ownerId);
    public List<House> findByRegion(String regionName);
    public List<House> findByAgencyId(Long agencyId);
    public List<House> findByOwnerId(Long ownerId);
    public List<House> findBetweenDates(Date startDate, Date endDate);


}
