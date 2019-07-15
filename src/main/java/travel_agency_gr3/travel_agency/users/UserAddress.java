package travel_agency_gr3.travel_agency.users;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress {

    private String street;
    private String city;
    private String country;
    private String zipCode;

}
