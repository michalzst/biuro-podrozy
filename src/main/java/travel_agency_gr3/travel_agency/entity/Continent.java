package travel_agency_gr3.travel_agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Continent extends BaseEntity{

    private String name;
    @OneToMany(mappedBy = "continent")
    private Set<Country> countryList = new HashSet<>();

}
