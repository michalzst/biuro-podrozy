package travel_agency_gr3.travel_agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import travel_agency_gr3.travel_agency.entity.City;
import travel_agency_gr3.travel_agency.entity.Continent;

public interface ContinentRepo<T extends Continent> extends JpaRepository<Continent, Long>, QuerydslPredicateExecutor<Continent> {
}
