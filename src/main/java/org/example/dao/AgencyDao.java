package org.example.dao;

import org.example.entities.Address;
import org.example.entities.Agency;

public interface AgencyDao {

    public String save(Agency agency, Address address);
    public Agency findAgencyByID(Long id);
    public String updateAgency(Agency agency, Long id);
    public String removeAgency(Long id);

}
