package travel_agency_gr3.travel_agency.datatables;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class DataTablesResponse<T> {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data = Collections.emptyList();
}
