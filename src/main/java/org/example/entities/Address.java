package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_gen")
    @SequenceGenerator(name = "address_gen",sequenceName = "address_seq",allocationSize = 1)
    private Long id;
    private String region;
    private String city;

    @Column(name = "street", nullable = false, unique = true)
    private String street;

    @OneToOne(mappedBy = "address")
    private Agency agency;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
    public Address(String region, String city, String street) {
        this.region = region;
        this.city = city;
        this.street = street;
    }
}
