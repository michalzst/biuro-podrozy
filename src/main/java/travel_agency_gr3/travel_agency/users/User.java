package travel_agency_gr3.travel_agency.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import travel_agency_gr3.travel_agency.entity.BaseEntity;


import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.STRING)
public class User extends BaseEntity {
    private String firstName;
    private String surName;
    private String username;
    private String passwordHash;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(name = "user_role")
    private Set<Role> roles;


    private UserAddress userAddress;
}
