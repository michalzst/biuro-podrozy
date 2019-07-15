package travel_agency_gr3.travel_agency.users;

import lombok.Getter;

@Getter
public enum Countries {

    POLAND("PL", "Polska"), GERMANY("DE", "Niemcy"), ENGLAND("ENG", "Anglia"), FRANCE("FRA", "Francja");

    private String symbol;
    private String plName;

    Countries(String symbol, String plName) {

        this.symbol = symbol;
        this.plName = plName;
    }
}
