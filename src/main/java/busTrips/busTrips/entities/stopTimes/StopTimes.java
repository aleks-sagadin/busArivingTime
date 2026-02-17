package busTrips.busTrips.entities.stopTimes;

import lombok.Data;

import java.sql.Time;

//trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_traveled,timepoint
//NORMAL_03_101_Return_22:10,22:10:00,22:10:00,2,1,,,,,
@Data
public class StopTimes {
    /**
     * unique
     */
    String tripId;
    Time arrivalTime;
    Time departureTime;
    /**
     * unique
     */
    int stopId;

    int stopSequence; //non negative
    String stopHeadsign;
    PickupType pickupType;
    DropOffType dropOffType;
    ContinuousDropOff continuousDropOff;
    float shapeDistTraveled; //non negative float
    TimePoint timePoints;


}
