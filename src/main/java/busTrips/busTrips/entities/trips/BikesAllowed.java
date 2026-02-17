package busTrips.busTrips.entities.trips;

import lombok.Getter;

public enum BikesAllowed {
    NO_BIKE_INFORMATION(0),
    CAN_TRIP_AT_LEAST_ONE_BICYCLE(1),
    NO_BICYCLE_ALLOWED(2),;

    @Getter
    private final int value;
    BikesAllowed(int value) {
        this.value = value;
    }
}
