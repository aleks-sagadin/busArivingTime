package busTrips.busTrips.entities.stopTimes;

import lombok.Getter;

public enum ContinuousDropOff {
    CONTINUOUS_DROP_OFF(0),
    NO__DROP_OFF(1),
    MUST_PHONE_AGENCY_DROP_OFF(2),
    MUST_COORDINATE_WITH_DRIVER_DROP_OFF(3);
    @Getter
    private final int value;

    ContinuousDropOff(int value) {
        this.value = value;
    }
}
