package busTrips.busTrips.entities;

import lombok.Getter;


public enum ArrivalTimes {

    ABSOLUTE("absolute"),
    RELATIVE("relative");

    @Getter
    private final String value;
    ArrivalTimes(String value) {
        this.value = value;
    }
}
