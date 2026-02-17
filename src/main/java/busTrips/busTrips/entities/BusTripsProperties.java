package busTrips.busTrips.entities;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 *  Class is not used
 */
@Configuration
@Data
public class BusTripsProperties {
    /**
     * · station_id (int): ID of the stop (e.g., 12345)
     */
    private int stationId;
    /**
     *· num_buses_per_line (int): The maximum number of upcoming arrivals to show per route.
     */
    private int numBusesPerLine;
    /**
     *· relative|absolute: Format of the arrival times:
     */
    private ArrivalTimes arrivalTimes;

    /**
     *  asc|desc
     */
    private String sortType;

}
