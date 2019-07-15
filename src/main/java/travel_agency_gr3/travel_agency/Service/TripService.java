package travel_agency_gr3.travel_agency.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel_agency_gr3.travel_agency.DTO.TripDTO;
import travel_agency_gr3.travel_agency.DTOBuilder.TripDTOBuilder;
import travel_agency_gr3.travel_agency.datatables.DataTablesOrder;
import travel_agency_gr3.travel_agency.datatables.DataTablesResponse;
import travel_agency_gr3.travel_agency.entity.City;
import travel_agency_gr3.travel_agency.entity.FoodType;
import travel_agency_gr3.travel_agency.entity.QTrip;
import travel_agency_gr3.travel_agency.entity.Trip;
import travel_agency_gr3.travel_agency.repository.TripRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TripService {
    @Autowired
    private TripDTOBuilder tripDTOBuilder;

    @Autowired
    private TripRepo<Trip> tripRepo;

    public void createNewTrip(String name, City destinationName, LocalDate dateOfDeparture, LocalDate dateOfReturn, FoodType foodType, Double addultPrice, Double childPrice,
                               Integer numberAdultPlaces, Integer numberChildPlaces, boolean promotion) {
        LocalDate start = LocalDate.from(dateOfDeparture);
        LocalDate rtn = LocalDate.from(dateOfReturn);

        Trip trip = new Trip();
        trip.setName(name);
        trip.setDestinationName(destinationName);
        trip.setDateOfDeparture(dateOfDeparture);
        trip.setDateOfReturn(dateOfReturn);
        trip.setNumberOfDays(rtn.getDayOfYear() - start.getDayOfYear());
        trip.setFoodType(foodType);
        trip.setAddultPrice(addultPrice);
        trip.setChildPrice(childPrice);
        trip.setNumberAdultPlaces(numberAdultPlaces);
        trip.setNumberChildPlaces(numberChildPlaces);
        trip.setPromotion(promotion);
        tripRepo.save(trip);
    }

    public void updateTrip(TripDTO tripDTO) {
        Trip t = tripDTOBuilder.buildEntity(tripDTO);
        tripRepo.save(t);
    }

    public Optional<Trip> findTrip(Long id) {
        return tripRepo.findById(id);
    }

    public List<Trip> findPromotionTrips() {
        return tripRepo.findPromotionTrip();
    }

    public List<Trip> findNotPromotionTrips() {
        return tripRepo.findNotPromotionTrip();
    }

    public List<Trip> findTripToEdit(String query, String foodType) {
        return findTripQuery(query, foodType);
    }

    private List<Trip> findTripQuery(String query, String productType) {
        if (StringUtils.isBlank(query) && StringUtils.isBlank(productType)) {
            return tripRepo.findAll();
        }
        if (StringUtils.isBlank(query)) {
            return tripRepo.findTripByFoodType(FoodType.valueOf(productType));
        }
        if (StringUtils.isBlank(productType)) {
            return tripRepo.findTripByName(query);
        }
        return tripRepo.findByNameAndFoodType(query, FoodType.valueOf(productType));
    }

    public List<TripDTO> findTripsForCustomer(String query, String foodType) {
        return findTripQuery(query, foodType)
                .stream()
                .filter(e -> ObjectUtils.defaultIfNull(e.getAddultPrice(), 0d) > 0d)
                .map(tripDTOBuilder::buildDto)
                .collect(Collectors.toList());
    }

    public Optional<TripDTO> findTriptById(Long id) {
        return tripRepo.findById(id).map(tripDTOBuilder::buildDto);
    }

    public DataTablesResponse<TripDTO> getTripDataTable(Integer start, Integer length, String sortColumn, String sortOrder, String searchText) {
        DataTablesResponse<TripDTO> dtResponse = new DataTablesResponse<>();
        Page<Trip> tripByName = findTripByName(searchText, start == 0 ? 0 : (start / length), length, getSort(sortColumn, sortOrder));
        dtResponse.setData(tripByName.getContent()
                .stream()
                .map(tripDTOBuilder::buildDto)
                .collect(Collectors.toList()));
        dtResponse.setRecordsTotal((int) tripByName.getTotalElements());
        dtResponse.setRecordsFiltered((int) tripByName.getTotalElements());
        return dtResponse;
    }

    private Sort getSort(String name, String direction) {
        return direction.equalsIgnoreCase(DataTablesOrder.Direction.asc.name()) ? Sort.by(name).ascending() : Sort.by(name).descending();
    }

    private Page<Trip> findTripByName(String query, int page, int size, Sort sort) {
        Function<String, Page<Trip>> supplierForNotBlankQuery = (q) -> tripRepo.findAll(QTrip.trip.name.likeIgnoreCase("%" + q + "%").and(QTrip.trip.numberAdultPlaces.goe(1)), PageRequest.of(page, size, sort));
        Function<String, Page<Trip>> supplierForBlankQuery = (q) -> tripRepo.findAll(QTrip.trip.numberAdultPlaces.goe(1), PageRequest.of(page, size, sort));
        return StringUtils.isBlank(query) ? supplierForBlankQuery.apply(query) : supplierForNotBlankQuery.apply(query);
    }
}
