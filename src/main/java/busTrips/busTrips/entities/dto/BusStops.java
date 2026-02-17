package busTrips.busTrips.entities.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class BusStops {

    String name; //name of station
    String tripId;
    String routeId;
    String routeName;
    LocalTime arrivingTime;
}
