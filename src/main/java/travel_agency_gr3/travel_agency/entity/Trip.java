package travel_agency_gr3.travel_agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Trip extends BaseEntity {
    @OneToOne
    private City destinationName;
    private LocalDate dateOfDeparture;
    private LocalDate dateOfReturn;
    private Integer numberOfDays;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private Double addultPrice;
    private Double childPrice;
    private Integer numberAdultPlaces;
    private Integer numberChildPlaces;
    private boolean promotion;
    private String name;
}
