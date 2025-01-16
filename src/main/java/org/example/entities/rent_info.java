package org.example.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="rent_infos")
public class rent_info {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "rent_info_gen")
    @SequenceGenerator(name = "rent_info_gen",sequenceName = "rent_info_seq",allocationSize = 1)
    private Long id;
    private Date checkIn;
    private Date checkOut;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Customer customer;



}
