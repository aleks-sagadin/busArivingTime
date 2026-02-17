package busTrips.busTrips.entities.stopTimes;

import lombok.Getter;

public enum PickupType {

    REGULARLY_SCHEDULED_PICKUP(0),
    NO_PICKUP_AVAILABLE(1),
    MUST_PHONE_AGENCY(2),
    MUST_COORDINATE_WITH_DRIVER(3);
    @Getter
    private final int value;

    PickupType(int value) {
        this.value = value;
    }
}
