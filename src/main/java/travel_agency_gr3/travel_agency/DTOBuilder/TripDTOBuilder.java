package travel_agency_gr3.travel_agency.DTOBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel_agency_gr3.travel_agency.DTO.TripDTO;
import travel_agency_gr3.travel_agency.Service.CityService;
import travel_agency_gr3.travel_agency.Service.TripService;
import travel_agency_gr3.travel_agency.entity.City;
import travel_agency_gr3.travel_agency.entity.Trip;
import travel_agency_gr3.travel_agency.repository.CityRepo;
import travel_agency_gr3.travel_agency.repository.TripRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TripDTOBuilder {
    @Autowired
    private TripRepo<Trip> tripRepo;

    @Autowired
    private CityService cityService;
    public TripDTO buildDto(Trip trip) {
        return TripDTO.builder()
                .id(trip.getId())
                .addultPrice(trip.getAddultPrice())
                .childPrice(trip.getChildPrice())
                .dateOfDeparture(trip.getDateOfDeparture().toString())
                .dateOfReturn(trip.getDateOfReturn().toString())
                .foodType(trip.getFoodType())
                .destinationId(cityService.findCity(trip.getDestinationName().getName()).getId())
                .name(trip.getName())
                .numberAdultPlaces(trip.getNumberAdultPlaces())
                .numberChildPlaces(trip.getNumberChildPlaces())
                .numberOfDays(trip.getNumberOfDays())
                .promotion(trip.isPromotion())
                .build();
    }

    public Trip buildEntity(TripDTO dto) {
        Trip trip;
        if (dto.getId() == null) {
            trip = new Trip();
        } else {
            trip = tripRepo.getOne(dto.getId());
        }

        trip.setAddultPrice(dto.getAddultPrice());
        trip.setChildPrice(dto.getChildPrice());
        trip.setDateOfDeparture(LocalDate.parse((dto.getDateOfDeparture())));
        trip.setDateOfReturn(LocalDate.parse(dto.getDateOfReturn()));
        trip.setFoodType(dto.getFoodType());
        trip.setDestinationName(cityService.findCity(dto.getDestinationName()));
        trip.setName(dto.getName());
        trip.setNumberAdultPlaces(dto.getNumberAdultPlaces());
        trip.setNumberChildPlaces(dto.getNumberChildPlaces());
        trip.setNumberOfDays(dto.getNumberOfDays());
        trip.setPromotion(dto.isPromotion());
        return trip;
    }
}
