package busTrips.busTrips.util;

import busTrips.busTrips.entities.dto.BusStops;
import busTrips.busTrips.entities.stops.Stop;
import busTrips.busTrips.service.FileProcessingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(args = {"1", "5", "relative"})
@ActiveProfiles("test")
class ReadFilesTest {

    public static final String  RELATIVE = "relative";
    @Autowired
    ReadFiles service;
    @Autowired
    FileProcessingService fileProcessingService;
    @Test
    void readStopFileTest() throws Exception {
        Stop stop = service.readStopFile(3);
        assertNotNull(stop);

    }

    @Test
    void readStopTimesTest() throws Exception{
        Stop stop = service.readStopFile(3);
        //false
        assertDoesNotThrow(() -> service.readStopTimes(stop,3,3,"ABC"));
        //true
        assertDoesNotThrow(() -> service.readStopTimes(stop,  3,  3, RELATIVE));


    }
    @Test
    void readTripsTest() throws Exception{
        List<BusStops> busStopsList = new ArrayList<>();
        BusStops busStops = new BusStops();
        busStops.setTripId("NORMAL_03_101_Return_22:10");
        busStopsList.add(busStops);
        assertDoesNotThrow(() -> service.readTrips( busStopsList));
        List<BusStops> returnedValue =   service.readTrips( busStopsList);
        assertEquals(1,returnedValue.size());

    }
    @Test
    void readRoutesTest() throws Exception{
        List<BusStops> busStopsList = new ArrayList<>();
        BusStops busStops = new BusStops();
        busStops.setTripId("NORMAL_03_101_Return_22:10");
        busStops.setRouteId("101");
        busStopsList.add(busStops);
        assertDoesNotThrow(() -> service.readRoutes( busStopsList));
    }
}