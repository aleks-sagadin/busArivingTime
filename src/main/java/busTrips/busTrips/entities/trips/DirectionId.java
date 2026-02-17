package busTrips.busTrips.entities.trips;

import lombok.Getter;

public enum DirectionId {

    TRAVEL_IN_ONE_DIRECTION(0),
    TRAVEL_IN_OPPOSITE_DIRECTION(1);

    @Getter
    private final int value;
    DirectionId(int value) {
        this.value = value;
    }
}
