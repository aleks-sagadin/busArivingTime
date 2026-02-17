package busTrips.busTrips.entities.stops;

import lombok.Data;

@Data
public class Stop {
    /**
     * unique
     */
    int stopId;
    String stopCode;
    String stopName;
    String stopDesc;
    long stopLat;
    long stopLon;

   // int zone_id;
   // String zone_name;
   // String zone_code;
   // String platfo

}
