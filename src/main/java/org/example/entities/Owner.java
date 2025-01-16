package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.entities.enums.Gender;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
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
    private Gender gender;

    @ManyToMany(mappedBy = "owners")
    private List<Agency>agencies;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE)
    private List<rent_info>rent_infos;

    @OneToMany(mappedBy = "owner",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<House>houses;


}

