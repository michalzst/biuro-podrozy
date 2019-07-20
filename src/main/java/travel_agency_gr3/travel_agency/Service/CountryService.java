package travel_agency_gr3.travel_agency.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel_agency_gr3.travel_agency.DTO.CountryDTO;
import travel_agency_gr3.travel_agency.DTOBuilder.CountryDTOBuilder;
import travel_agency_gr3.travel_agency.entity.City;
import travel_agency_gr3.travel_agency.entity.Country;
import travel_agency_gr3.travel_agency.repository.CountryRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CountryService {

    @Autowired
    private CountryDTOBuilder countryDTOBuilder;

    @Autowired
    private CountryRepo<Country> countryRepo;

    public void createNewCountry(String name){
        Country country = new Country();
        country.setName(name);
        countryRepo.save(country);
    }

    public void updateCountry(CountryDTO countryDTO){
        Country country = countryDTOBuilder.buildCountryEntity(countryDTO);
        countryRepo.save(country);
    }
//    public Optional<Country> findCountryById (Long id) {return  countryRepo.findById(id);}
    public Country findCountryByName(String name){
        return countryRepo.findCountryByName(name);
    }
    public Set findAllCountry() {
        Set<String> countryListNames = new HashSet<>();
        for (Country c:countryRepo.findAll()) {
            countryListNames.add(c.getName().trim());
        }
        return countryListNames ;
    }
    public Country findCountry(String name){
        return countryRepo.findCountryByNameList(name).get(0);
    }

}
