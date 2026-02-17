package busTrips.busTrips.entities.routes;

import lombok.Data;

//route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color
//101,Short name,101,,,3,,964E00,000000
@Data
public class Routes {
    /**
     * unique
     */
    int routeId;
    String agencyId;
    String routeShortName;
    String routeLongName;
    String routeDesc;
    RouteType routeType;
    String routeUrl; //optional
    String routeColor;
    String routeTextColor;




}
