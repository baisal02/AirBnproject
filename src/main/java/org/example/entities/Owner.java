package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.entities.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "owner_gen")
    @SequenceGenerator(name = "owner_gen",sequenceName = "owner_seq",allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @ManyToMany(mappedBy = "owners")
    private List<Agency>agencies =new ArrayList<>();

    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE)
    private List<rent_info>rent_infos = new ArrayList<>();

    @OneToMany(mappedBy = "owner",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<House>houses = new ArrayList<>();



    @Override
    public String toString() {
        return "Owner{" +
                "houses=" + houses +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", rent_infos=" + rent_infos +
                '}';
    }
}

