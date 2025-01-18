package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.entities.enums.FamilyStatus;
import org.example.entities.enums.Gender;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen",sequenceName = "customer_seq",allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private Date dateOfBirth;
    private Gender gender;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;

    @OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<rent_info> rentInfoList = new ArrayList<>();

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", nationality='" + nationality + '\'' +
                ", familyStatus=" + familyStatus +
                ", rentInfoList=" + rentInfoList +
                '}';
    }
}
