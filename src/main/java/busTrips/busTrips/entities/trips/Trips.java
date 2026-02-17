package busTrips.busTrips.entities.trips;

import lombok.Data;

@Data
public class Trips {
    /**
     * unique
     */
    int routeId; //101
    /**
     * unique
     */
    int serviceId; //1
    /**
     * unique
     */
    String tripId; //NORMAL_03_101_Return_22:10
    String tripHeadsign;//Uhud battlefield
    String tripShortName;//
    DirectionId directionId;//1
    String blockId; //101_03
    String shapeId;//41
    WheelChairAccessible wheelchairAccessible;//
    BikesAllowed bikesAllowed;//
    String duty;//101_032
    int dutySequenceNumber;//24
    int runSequenceNumber;//48
}
