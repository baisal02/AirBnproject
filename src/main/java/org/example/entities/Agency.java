package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="agencies")
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "agency_gen")
    @SequenceGenerator(name = "agency_gen",sequenceName = "agency_seq",allocationSize=1)
    private Long id;
    private String name;
    private String phoneNumber;



    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Address address;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Owner>owners = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<rent_info>rent_infos = new ArrayList<>();

    @Override
    public String toString() {
        return "Agency{" +
                "address=" + address +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", owners=" + owners +
                ", rent_infos=" + rent_infos +
                '}';
    }
}
