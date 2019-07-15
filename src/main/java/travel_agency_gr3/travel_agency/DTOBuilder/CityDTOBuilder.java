package travel_agency_gr3.travel_agency.DTOBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel_agency_gr3.travel_agency.DTO.CityDTO;
import travel_agency_gr3.travel_agency.entity.City;
import travel_agency_gr3.travel_agency.repository.CityRepo;

@Service
public class CityDTOBuilder {

    @Autowired
    private CityRepo<City> cityRepo;

    public CityDTO buildCityDTO(City city) {
        return CityDTO.builder()
                .id(city.getId())
                .country(city.getCountry())
                .name(city.getName())
                .build();
    }

    public City buildCityEntity(CityDTO dto) {
        City city;
        if (dto.getId() == null) {
            city = new City();
        } else {
            city = cityRepo.getOne(dto.getId());
        }
        city.setName(dto.getName());
        city.setCountry(dto.getCountry());
        return city;
    }
}


