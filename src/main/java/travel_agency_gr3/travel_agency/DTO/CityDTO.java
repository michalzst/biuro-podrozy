package travel_agency_gr3.travel_agency.DTO;


import lombok.*;
import travel_agency_gr3.travel_agency.entity.Country;

import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private Long id;

    @Pattern(regexp = "^[\\p{Lu}][\\p{Ll}]{2,19}$", message = "Wymagane od 3 do 20 znaków (pierwsza litera duża, reszta małe).")
    private String name;

    private String countryName;
    private Long countryId;

}
