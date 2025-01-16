package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_gen")
    @SequenceGenerator(name = "address_gen",sequenceName = "address_seq",allocationSize = 1)
    private Long id;
    private String region;
    @Column(name = "street", nullable = false, unique = true)
    private String street;
    @OneToOne(mappedBy = "address")
    private Agency agency;
}
