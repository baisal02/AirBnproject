package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.entities.enums.FamilyStatus;
import org.example.entities.enums.Gender;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen",sequenceName = "customer_seq")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private Date dateOfBirth;
    private Gender gender;
    private String nationality;
    private FamilyStatus familyStatus;

    @OneToMany(mappedBy = "customer")
    private List<rent_info> rentInfoList;

}
