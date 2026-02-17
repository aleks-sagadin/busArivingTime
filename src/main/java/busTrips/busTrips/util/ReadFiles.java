package busTrips.busTrips.util;

import busTrips.busTrips.entities.dto.BusStops;
import busTrips.busTrips.entities.stopTimes.StopTimes;
import busTrips.busTrips.entities.stops.Stop;
import busTrips.busTrips.service.FileProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReadFiles {

    public static final int HOUR = 2;

    @Value("classpath:gtfs/stops.txt")
    private Resource stopsResource;

    @Value("classpath:gtfs/stop_times.txt")
    private Resource stopsTimesResource;

    @Value("classpath:gtfs/trips.txt")
    private Resource tripsResource;

    @Value("classpath:gtfs/routes.txt")
    private Resource routesResource;

    // then read stops and get id and name of stop
    private final FileProcessingService fileProcessingService;


    public Stop readStopFile(int number) throws Exception {

        //Resource resource = new ClassPathResource("gtfs/stops.txt");
        //File file = resource.getFile();
        Stop stop = new Stop();
        // try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){ //this  was my, but with AI I change to  InputStream
        try (InputStream inputStream = stopsResource.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            br.readLine(); //skip first line

            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                String[] value = strLine.split(",", -1);
                stop.setStopId(Integer.parseInt(value[0]));
                stop.setStopName(value[2]);
                if (number == stop.getStopId()) {
                    break; // close
                }
            }
        } catch (FileNotFoundException f) {
            log.error("[readStopFile] Error: {}", f.getMessage());
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stop;
    }

    //
    public List<BusStops> readStopTimes(Stop stop, int number, int numBusesPerLine, String time) throws Exception {

        LocalTime currentTime = LocalTime.now();
        LocalTime currentTimePlusTwo = currentTime.plusHours(HOUR);
        List<BusStops> busStopsList = new ArrayList<>();

        // Resource resource1 = new ClassPathResource("gtfs/stop_times.txt");
        // File file1 = resource1.getFile();
        // try(BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)))){ //this  was my, but with AI I change to  InputStream
        try (InputStream inputStream = stopsTimesResource.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            br.readLine(); //skip first line

            String strLine1;
            //Read File Line By Line
            int counter = 0;// counting until is max number of lines
            while ((strLine1 = br.readLine()) != null && counter < numBusesPerLine) {
                String[] value = strLine1.split(",", -1);
                StopTimes stopsTimes = new StopTimes();
                //if is empty go to next
                if (value[3].isEmpty()) {
                    continue;
                }
                stopsTimes.setStopId(Integer.parseInt(value[3]));
                stopsTimes.setTripId(value[0]); //this is for reading trips
                if (number == stopsTimes.getStopId()) {
                    stopsTimes.setArrivalTime(Time.valueOf(value[1]));
                    counter = fileProcessingService.getNumberOfBusesInList(time, currentTime, currentTimePlusTwo, stop, busStopsList, counter, stopsTimes);

                }
            }
        } catch (FileNotFoundException f) {
            log.error("[ readStopTimes] Error: {}", f.getMessage());
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return busStopsList;
    }

    /**
     * Method read from trips.txt and get route_id form trips_id
     */
    public List<BusStops> readTrips(List<BusStops> busStopsList) throws Exception {
        //Resource resource1 = new ClassPathResource("gtfs/trips.txt");
        //File file = resource1.getFile();
        // try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) { //this  was my, but with AI I change to  InputStream
        try (InputStream inputStream = stopsResource.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            br.readLine(); //skip first line
            String strLine1;
            int counter = 0;
            while ((strLine1 = br.readLine()) != null) {
                String[] value = strLine1.split(",", -1);
                if (counter > busStopsList.size()) {
                    break;
                }
                for (BusStops busStop : busStopsList) {
                    if (Objects.equals(busStop.getTripId(), value[2])) {
                        busStop.setRouteId(value[0]);
                        counter++;
                    }
                }
            }

        } catch (IOException e) {
            log.error("[ readTrips ] Error: {}", e.getMessage());
            throw new FileNotFoundException();
        }
        return busStopsList;
    }

    /**
     * Method read from route.txt and get name from routes
     */
    public void readRoutes(List<BusStops> busStopsList) throws Exception {
        //  Resource resource1 = new ClassPathResource("gtfs/routes.txt");
        //  File file = resource1.getFile();
        // try(BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) { //this  was my, but with AI I change to  InputStream
        try (InputStream inputStream = stopsResource.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            br.readLine(); //skip first line
            String strLine1;
            while ((strLine1 = br.readLine()) != null) {
                String[] value = strLine1.split(",", -1);
                for (BusStops busStop : busStopsList) {
                    if (Objects.equals(busStop.getRouteId(), value[0])) {
                        busStop.setRouteName(value[1]);
                    }
                }
            }
        } catch (IOException e) {
            log.error("[ readRoutes] Error: {}", e.getMessage());
            throw new FileNotFoundException();
        }
    }

}
