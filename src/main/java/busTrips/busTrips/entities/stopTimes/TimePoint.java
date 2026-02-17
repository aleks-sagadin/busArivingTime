package busTrips.busTrips.entities.stopTimes;

import lombok.Getter;

public enum TimePoint {

    TIMES_ARE_CONSIDERED_APPROXIMATE(0),
    TIMES_ARE_CONSIDERED_EXACT(1);
    @Getter
    private final int value;

    TimePoint(int value) {
        this.value = value;
    }
}
