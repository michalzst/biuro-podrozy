package travel_agency_gr3.travel_agency.datatables;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTablesColumn {

    private String name;
    private boolean searchable;
    private boolean orderable;
    private DataTablesSearch search;
}
