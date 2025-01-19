package org.example.dao;

import org.example.entities.House;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface HouseDao {
    public void save(House house);
    public void saveHouseRelatedToOwner(House house,Long ownerId);
    public List<House> getHousesByRegion(String regionName);
    public List<House> getHousesByAgencyId(Long agencyId);
    public List<House> getHousesByOwnerId(Long ownerId);
    public List<House> findBetweenDates(LocalDate startDate, LocalDate endDate);
    public void deleteHouseById(Long houseId);


}
