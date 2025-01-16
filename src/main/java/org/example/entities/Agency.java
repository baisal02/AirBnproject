package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
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
    private List<Owner>owners;

    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<rent_info>rent_infos;


}
