package travel_agency_gr3.travel_agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import travel_agency_gr3.travel_agency.entity.FoodType;
import travel_agency_gr3.travel_agency.entity.Trip;

import java.util.List;
import java.util.Optional;


@Repository
public interface TripRepo<T extends Trip> extends JpaRepository<Trip, Long>, QuerydslPredicateExecutor<Trip> {

    Optional<T> findTripById(Long id);

    List<T> findTripByFoodType(FoodType foodType);

    @Query ("SELECT t FROM Trip t where (t.promotion)=true")
    List<T> findPromotionTrip();

    @Query ("SELECT t FROM Trip t where (t.promotion)=false ")
    List<T> findNotPromotionTrip();

    @Query("select t from Trip t where upper(t.name) like concat('%',upper(?1),'%')")
    List<T> findTripByName(String searchText);

    @Query("select t from Trip t where upper(t.name) like concat('%',upper(?1),'%') and t.foodType = ?2")
    List<T> findByNameAndFoodType(String searchText, FoodType foodType);
}

