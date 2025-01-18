package org.example.dao;


import org.example.entities.House;
import org.example.entities.Owner;

import java.util.List;

public interface OwnerDao {
    public void saveOwnerWithHouse(Owner owner, House house);
    public void saveOwner(Owner owner);
    public void deleteOwner(Long ownerId);
    public void assignOwnerToAgency(Long ownerId, Long agencyId);
    public List<Owner> getOwnersByAgency(Long agencyId);
    public void getOwnersNameAge();
}
