package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.entities.enums.HouseType;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "house_gen")
    @SequenceGenerator(name = "house_gen",sequenceName = "house_seq",allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    private BigDecimal price;
    private Double rating;
    private String description;
    private int room;
    private boolean haveFurniture;

    @ManyToOne
    private Owner owner;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Address address;

    @OneToOne
    private rent_info rent_info;


    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", houseType=" + houseType +
                ", price=" + price +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", room=" + room +
                ", haveFurniture=" + haveFurniture +
                ", address=" + address +
                ", rent_info=" + rent_info +
                '}';
    }
}
