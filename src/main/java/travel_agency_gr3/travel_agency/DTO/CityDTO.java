package travel_agency_gr3.travel_agency.DTO;


import lombok.*;
import travel_agency_gr3.travel_agency.entity.Country;

import javax.persistence.OneToOne;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private Long id;
    private String name;
    private Country country;

}
