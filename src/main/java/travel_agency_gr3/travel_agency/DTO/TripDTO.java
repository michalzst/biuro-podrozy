package travel_agency_gr3.travel_agency.DTO;

import lombok.*;

import org.springframework.format.annotation.NumberFormat;
import travel_agency_gr3.travel_agency.entity.FoodType;


import javax.validation.constraints.*;
import java.util.Optional;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {
    private Long id;
    private Long destinationId;
    @Pattern(regexp = "^[\\p{Lu}][\\p{Ll}]{2,}$", message = "Wymagane przynajmniej 3 znaki(pierwsza litera duża, reszta małe).")
    private String name;

    private String destinationName;

    @Pattern(regexp = "^(19|20)[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|(1|2)[0-9]|3[0-1])$", message = "Zły format. Data powinna być podana w formacie RRRR-MM-DD")
    private String dateOfDeparture;

    @Pattern(regexp = "^(19|20)[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|(1|2)[0-9]|3[0-1])$", message = "Zły format. Data urodzin powinna być podana w formacie RRRR-MM-DD")
    private String dateOfReturn;

    private Integer numberOfDays;

    @NotNull(message = "Pole musi zostać wypełnione")
    private FoodType foodType;

    @NotNull(message = "Pole musi zostać wypełnione")
    @Max(9)
    private Double addultPrice;

    @NotNull(message = "Pole musi zostać wypełnione")
    @Max(9)
    private Double childPrice;

    @NotNull(message = "Pole musi zostać wypełnione")
    @Max(9)
    private Integer numberAdultPlaces;

    @NotNull(message = "Pole musi zostać wypełnione")
    @Max(9)
    private Integer numberChildPlaces;
    private boolean promotion;


    public String getFoodTypeString(){
        return Optional.ofNullable(foodType).map(e->e.getType()).orElse("");
    }

}
