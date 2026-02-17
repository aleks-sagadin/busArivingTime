package busTrips.busTrips.service;

import busTrips.busTrips.entities.dto.BusStops;
import busTrips.busTrips.entities.stopTimes.StopTimes;
import busTrips.busTrips.entities.stops.Stop;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static busTrips.busTrips.service.FileProcessingService.sortBusArrivingTimes;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@ActiveProfiles("test")
class FileProcessingServiceTest {

    FileProcessingService fileProcessingService;

   @BeforeEach
   void setUp() {
       fileProcessingService = new FileProcessingService();
   }

    LocalTime currentTime = LocalTime.of(13, 0);
    LocalTime stopTimes = LocalTime.of(13, 20);

    /**
     * test  for getTime value method
     **/
    @Test
    void getNumberOfBusesInListTest() {

        LocalTime validResult = LocalTime.of(0, 20);
        LocalTime notValidResult = LocalTime.of(0, 21);

        assertEquals(validResult, fileProcessingService.timeBetween(currentTime, stopTimes));
        assertNotEquals(notValidResult, fileProcessingService.timeBetween(currentTime, stopTimes));


    }
    /**
     * test  for getTime value method
     **/
    @Test
    void getNumberOfBusesInListAfterTenPmTest() {
        LocalTime currentTimeAfterTenPm = LocalTime.of(22, 30);
        LocalTime stopTimesAfterTenPM = LocalTime.of(23, 20);
        List<BusStops> busStopsList =  new ArrayList<>();
        int counter =0;
        StopTimes stopsTimes = new StopTimes();
        stopsTimes.setArrivalTime(Time.valueOf(stopTimesAfterTenPM));
        Stop stop = new Stop();
        assertEquals(1, fileProcessingService.getNumberOfBusesInList("relative",currentTimeAfterTenPm, stopTimesAfterTenPM,stop,busStopsList,counter,stopsTimes));
        StopTimes stopsTimes1 = new StopTimes();
        LocalTime validPlusTwo = LocalTime.of(0, 30);
        LocalTime validInNewDay = LocalTime.of(0, 21);
        stopsTimes1.setArrivalTime(Time.valueOf(validInNewDay));
        assertEquals(1, fileProcessingService.getNumberOfBusesInList("relative",currentTimeAfterTenPm, validPlusTwo,stop,busStopsList,counter,stopsTimes1));


    }

    @Test
    void fillBusStopTest() {
        Stop stop = new Stop();
        StopTimes st = new StopTimes();
        st.setArrivalTime(Time.valueOf(stopTimes));
     //   assertThrows(InvalidValueException.class, () -> fileProcessingService.fillBusStop(stop,  currentTime,  st, "time"));
      assertDoesNotThrow(() -> fileProcessingService.fillBusStop(stop,  currentTime,  st, "absolute"));
     // assertDoesNotThrow(() -> fileProcessingService.fillBusStop(stop,  currentTime,  st, "relative"));
     // currentTime = LocalTime.of(23, 0);
     // assertDoesNotThrow(() -> fileProcessingService.fillBusStop(stop,  currentTime,  st, "relative"));


    }

    @Test
    void sortBusStopsTest() {
        String sortType = "asc";
        String sortTypeFail = "aaa";
        List<BusStops> busStopsList = generateBusStopList();
        sortBusArrivingTimes( busStopsList, sortTypeFail);
        log.info("\b WITHOUT SORT ARG: ");
        busStopsList.forEach(aTime -> log.info("Next arrival from route: {}  at: {}",aTime.getRouteId(), aTime.getArrivingTime()));
        sortBusArrivingTimes( busStopsList, sortType);
        log.info("\b ASC:");
        busStopsList.forEach(aTime -> log.info("Next arrival from route: {}  at: {}",aTime.getRouteId(), aTime.getArrivingTime()));
        log.info("\b DESC:");
        sortType = "desc";
        sortBusArrivingTimes( busStopsList, sortType);
        busStopsList.forEach(aTime -> log.info("Next arrival from route: {}  at: {}",aTime.getRouteId(), aTime.getArrivingTime()));
    }


    List<BusStops> generateBusStopList(){
        List<BusStops> busStopsList = new ArrayList<>();
        LocalTime time1 = LocalTime.of(15, 30);
        LocalTime time2 = LocalTime.of(15, 50);
        LocalTime time3 = LocalTime.of(16, 10);
        BusStops stop = new BusStops();
        stop.setArrivingTime(time1);
        stop.setArrivingTime(time3);
        stop.setArrivingTime(time2);

        BusStops busStop = new BusStops();
        busStop.setArrivingTime(time1);
        BusStops busStop1 = new BusStops();
        busStop1.setArrivingTime(time3);
        BusStops busStop2 = new BusStops();
        busStop2.setArrivingTime(time2);
        busStopsList.add(busStop);
        busStopsList.add(busStop1);
        busStopsList.add(busStop2);
        return busStopsList;
    }

}