package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.entities.enums.HouseType;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "house_gen")
    @SequenceGenerator(name = "house_gen",sequenceName = "house_seq")
    private Long id;
    private HouseType houseType;
    private BigDecimal price;
    private Double rating;
    private String description;
    private int room;
    private boolean haveBoolean;

    @ManyToOne
    private Owner owner;

    @OneToOne
    private Address address;

    @OneToOne
    private rent_info rent_info;

}
