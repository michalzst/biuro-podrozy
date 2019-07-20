package travel_agency_gr3.travel_agency.DTOBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel_agency_gr3.travel_agency.DTO.CountryDTO;
import travel_agency_gr3.travel_agency.entity.Country;
import travel_agency_gr3.travel_agency.repository.CountryRepo;

@Service
public class CountryDTOBuilder {
    @Autowired
    CountryRepo<Country> countryRepo;

    public CountryDTO buildCountryDTO (Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }

    public Country buildCountryEntity (CountryDTO dto) {
        Country country;
        if(dto.getId() == null) {
            country = new Country();
        } else {
            country = countryRepo.getOne(dto.getId());
        }
        country.setName(dto.getName());
        return country;
    }
}
