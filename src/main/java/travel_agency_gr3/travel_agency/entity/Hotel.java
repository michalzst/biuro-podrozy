package travel_agency_gr3.travel_agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hotel extends BaseEntity{

    private String name;
    private Integer hotelStandard;
    private String description;

    @OneToOne
    private City city;
}
