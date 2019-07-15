package travel_agency_gr3.travel_agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class City extends BaseEntity{


    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

}
