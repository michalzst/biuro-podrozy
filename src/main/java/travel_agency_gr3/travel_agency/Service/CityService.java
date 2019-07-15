package travel_agency_gr3.travel_agency.Service;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel_agency_gr3.travel_agency.DTO.CityDTO;
import travel_agency_gr3.travel_agency.DTOBuilder.CityDTOBuilder;
import travel_agency_gr3.travel_agency.entity.City;
import travel_agency_gr3.travel_agency.entity.Country;
import travel_agency_gr3.travel_agency.repository.CityRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CityService {
    @Autowired
    private CityDTOBuilder cityDTOBuilder;

    @Autowired
    private CityRepo<City> cityRepo;

    public void createNewCity(Country country,
                              String name
    ) {
        City city = new City();
        city.setCountry(country);
        city.setName(name);
        cityRepo.save(city);
    }
    public void updateCity(CityDTO cityDTO){
        City c = cityDTOBuilder.buildCityEntity(cityDTO);
        cityRepo.save(c);
    }
    public void deleteCity(Long id){
        cityRepo.deleteById(id);
    }

    public City findCity(String name){
        return cityRepo.findCityByNameList(name).get(0);
    }
    public Set findAllCity() {
        Set<String> cityListNames = new HashSet<>();
        for (City c:cityRepo.findAll()) {
            cityListNames.add(c.getName().trim());
        }
        return cityListNames ;
    }

}
