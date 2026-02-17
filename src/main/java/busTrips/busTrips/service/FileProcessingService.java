package busTrips.busTrips.service;

import busTrips.busTrips.entities.dto.BusStops;
import busTrips.busTrips.entities.stopTimes.StopTimes;
import busTrips.busTrips.entities.stops.Stop;
import busTrips.busTrips.util.InvalidValueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;


@Slf4j
@Service
public class FileProcessingService {

    /**
     * Method get number of times depends on counter value
     */

    public int getNumberOfBusesInList(String time, LocalTime currentTime, LocalTime currentTimePlusTwo, Stop stop, List<BusStops> busStopsList,
                                      int counter, StopTimes stopsTimes) {
        if (currentTime.getHour() >= 22) {
            if (currentTime.isBefore(stopsTimes.getArrivalTime().toLocalTime()) ||
                    stopsTimes.getArrivalTime().toLocalTime().isBefore(currentTimePlusTwo)) {
                busStopsList.add(fillBusStop(stop, currentTime, stopsTimes, time));
                counter++;
            }
        } else {
            if (currentTime.isBefore(stopsTimes.getArrivalTime().toLocalTime()) &&
                    currentTimePlusTwo.isAfter(stopsTimes.getArrivalTime().toLocalTime())) {
                //fill bus stops
                busStopsList.add(fillBusStop(stop, currentTime, stopsTimes, time));
                counter++;
            }
        }
        return counter;
    }



    /**
     * Method calculate time between endTime and startTime
     */
    public LocalTime timeBetween(LocalTime currentTime, LocalTime stopTimes) {
        if (log.isDebugEnabled()) {
            log.debug("[timeBetween] Current time : {} and stopTime {}", currentTime , stopTimes);
        }
        Duration duration = Duration.between(currentTime, stopTimes);
        //get hours and minutes
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        LocalTime localTime = LocalTime.of((int) hours, (int) minutes, (int)seconds);
        return localTime;
    }

    /**
     *  Method calculate after 10PM because of new day and time start again from 00:00
     */
    public LocalTime timeBetweenAfterTenPm(LocalTime currentTime, LocalTime stopTimes) {
        LocalTime lt = LocalTime.MAX; // 00:00
        LocalTime ltForNewDay = LocalTime.MIDNIGHT; // 00:00
        // MAX day time minus current time
        Duration unitlEndOfTheDay = Duration.between(currentTime, lt);
        log.info("[timeBetweenAfterTenPm] return time: {}", lt);
        long hours = unitlEndOfTheDay.toHours();
        long minutes = unitlEndOfTheDay.toMinutes() % 60;
        long seconds = unitlEndOfTheDay.toMinutes() % 60;
        LocalTime localTime = LocalTime.of((int) hours, (int) minutes,(int)seconds);
        // from midnight until stopTimes

        Duration inNewDay = Duration.between(ltForNewDay, stopTimes);
        log.info("[timeBetweenAfterTenPm] return time: {}", lt);
        long hoursNewDay = inNewDay.toHours();
        long minutesNewDay = inNewDay.toMinutes() % 60;
        long secondsNewDay = inNewDay.toMinutes() % 60;
        localTime = localTime.plusHours(hoursNewDay);
        localTime = localTime.plusMinutes(minutesNewDay);
        localTime = localTime.plusSeconds(secondsNewDay);

        //this is because LocalTime.MAX does not count minute from 00:59 to 00:00
        localTime = localTime.plusMinutes(1);

        log.info("[timeBetweenAfterTenPm] END OF METHOD: {}", localTime);
        return localTime;
    }


    /**
     * Method fill object for busStop
     */
    public BusStops fillBusStop(Stop stop, LocalTime currentTime, StopTimes stopTime, String time) {
        BusStops busStop = new BusStops();
        LocalTime stopTimes = stopTime.getArrivalTime().toLocalTime();
        busStop.setName(stop.getStopName());
        if ("absolute".equals(time)) {
            busStop.setArrivingTime(stopTimes);
            busStop.setTripId(stopTime.getTripId());
        } else if ("relative".equals(time)) {

            //if current is after 10PM
            if (currentTime.getHour() >= 22) {
                busStop.setArrivingTime(timeBetweenAfterTenPm(currentTime, stopTimes));
                //add route_id
                busStop.setTripId(stopTime.getTripId());

            } else {
                busStop.setArrivingTime(timeBetween(currentTime, stopTimes));
                //add route_id
                busStop.setTripId(stopTime.getTripId());
            }
        }else {
            log.error("[timeBetweenAfterTenPm] Invalid time parameter value. It can be only relative|absolute");
            throw new InvalidValueException("Invalid time parameter value. It can be only relative|absolute");
        }

        return busStop;
    }

    /**
     * method sort arriving times, depends on sortType value(optional param)
     */
    public static void sortBusArrivingTimes(List<BusStops> busStopsList, String sortType) {
        if(sortType.equalsIgnoreCase("asc")) {
            busStopsList.sort(Comparator.comparing(BusStops::getArrivingTime));
        }else if(sortType.equalsIgnoreCase("desc")) { // reverse order
            busStopsList.sort(Comparator.comparing(BusStops::getArrivingTime).reversed());
        }
    }

    public static void checkValueSortingArg(String sortOrder){
        if(!sortOrder.equals("asc") && !sortOrder.equals("desc")){
            log.info("[checkValueSortingArg] value of last argument[4] can be only asc or desc");
            throw new InvalidValueException("Invalid value for last argument[4]");
        }
    }


}
