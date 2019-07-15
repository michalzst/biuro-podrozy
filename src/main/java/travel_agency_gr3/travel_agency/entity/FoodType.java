package travel_agency_gr3.travel_agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum FoodType {
    BB("BB"), HB("HB"), FB("FB"), AL("AL");

    private String type;

    FoodType(String type) {
        this.type = type;
    }
}
