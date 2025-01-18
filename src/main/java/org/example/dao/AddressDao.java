package org.example.dao;

import org.example.entities.Address;
import org.example.entities.Agency;

import java.util.List;
import java.util.Map;

public interface AddressDao {
    public Map<Address, Agency> getAddress_Agencies();
    public int numberOfAgenciesInCity(String cityName);
    public Map<String, List<Agency>> groupByRegion() ;
}
