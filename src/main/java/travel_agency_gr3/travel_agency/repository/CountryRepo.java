package travel_agency_gr3.travel_agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import travel_agency_gr3.travel_agency.entity.Country;
import travel_agency_gr3.travel_agency.entity.Trip;

public interface CountryRepo extends JpaRepository<Country, Long>, QuerydslPredicateExecutor<Country> {


}
