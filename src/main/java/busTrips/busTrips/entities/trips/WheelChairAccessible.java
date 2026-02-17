package busTrips.busTrips.entities.trips;

import lombok.Getter;

public enum WheelChairAccessible {
    NO_ACCESSIBILITY(0),
    CAN_TRIP_AT_LEAST_ONE_RIDER_IN_A_WHEELCHAIR(1),
    NO_RIDER_IN_WHEELCHAIRS(2),;

    @Getter
    private final int value;
    WheelChairAccessible(int value) {
        this.value = value;
    }

}
