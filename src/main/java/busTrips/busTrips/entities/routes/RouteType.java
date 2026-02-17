package busTrips.busTrips.entities.routes;

import lombok.Getter;

public enum RouteType {

    TRAM(0),
    SUBWAY(1),
    RAIL(2),
    BUS(3),
    FERRY(4),
    CABLE_TRAM(5),
    AERIAL_LIFT(6),
    FUNICULAR(7),
    TROLLEYBUS(11),
    MONORAIL(12);
    @Getter
    private final int value;

    RouteType(int value) {
        this.value = value;
    }
}
