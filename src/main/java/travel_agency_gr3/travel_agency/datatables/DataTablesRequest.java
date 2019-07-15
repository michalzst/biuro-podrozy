package travel_agency_gr3.travel_agency.datatables;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class DataTablesRequest {

    private Integer draw;
    private Integer start;
    private Integer length;
    private DataTablesSearch search;
    private List<DataTablesColumn> columns = Collections.emptyList();
    private List<DataTablesOrder> order = Collections.emptyList();
}