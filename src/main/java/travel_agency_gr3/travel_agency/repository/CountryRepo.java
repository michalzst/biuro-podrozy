package travel_agency_gr3.travel_agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import travel_agency_gr3.travel_agency.entity.City;
import travel_agency_gr3.travel_agency.entity.Country;
import travel_agency_gr3.travel_agency.entity.Trip;

import java.util.List;

public interface CountryRepo<T extends Country> extends JpaRepository<Country, Long>, QuerydslPredicateExecutor<Country> {

    @Query("SELECT c FROM Country c where (c.name)=?1")
    Country findCountryByName(String name);

    @Query("SELECT c FROM Country c where (c.name)=?1")
    Long findCountryByNameFromId(String name);

    @Query("SELECT c FROM Country c where (c.name)=?1")
    List<Country> findCountryByNameList(String name);
}
