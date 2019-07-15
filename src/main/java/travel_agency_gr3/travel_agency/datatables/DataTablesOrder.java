package travel_agency_gr3.travel_agency.datatables;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTablesOrder {


    public enum Direction {
        asc, desc
    }

    private Integer column;
    private Direction dir;



}
