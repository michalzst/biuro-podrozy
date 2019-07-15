package travel_agency_gr3.travel_agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import travel_agency_gr3.travel_agency.entity.City;
import travel_agency_gr3.travel_agency.entity.Trip;

import java.util.List;

public interface CityRepo<T extends City> extends JpaRepository<City, Long>, QuerydslPredicateExecutor<City> {

    @Query("SELECT c FROM City c where (c.name)=?1")
    City findCityByName(String name);

    @Query("SELECT c FROM City c where (c.name)=?1")
    Long findCityByNameFromId(String name);

    @Query("SELECT c FROM City c where (c.name)=?1")
    List<City> findCityByNameList(String name);
}
