package busTrips.busTrips.runner;

import busTrips.busTrips.entities.dto.BusStops;
import busTrips.busTrips.entities.stops.Stop;
import busTrips.busTrips.util.InvalidValueException;
import busTrips.busTrips.util.ReadFiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

import static busTrips.busTrips.service.FileProcessingService.checkValueSortingArg;
import static busTrips.busTrips.service.FileProcessingService.sortBusArrivingTimes;

@Slf4j
@Component
@Profile("!test")
public class InitialRunner implements CommandLineRunner {

    private final ReadFiles readFiles;

    public InitialRunner(ReadFiles readFiles) {
        this.readFiles = readFiles;
    }

    @Override
    public void run(String... args) throws Exception {

        //4 argument is optional
        if (args.length < 3 ) {
            log.error("[run] Problems with arguments. Usage: <station_id> <num_buses> <relative|absolute>");
            throw new IllegalArgumentException("Usage: <station_id> <num_buses> <relative|absolute>");

        }
        String sortOrder = "";
        // for 4 parameter <asc|desc>
        if (args.length >= 4) {
            sortOrder = args[3];
            checkValueSortingArg(sortOrder);
        }
        try {
            int number = Integer.parseInt(args[0]);
            int numberMax = Integer.parseInt(args[1]);
            //read file stop
            Stop s = readFiles.readStopFile(number);
            //get stops_times from file, and put in list
            List<BusStops> busStopsList = readFiles.readStopTimes(s, number, numberMax, args[2]);
            //get routeId from tripId
            busStopsList = readFiles.readTrips(busStopsList);
            //get short names from route.txt
            readFiles.readRoutes(busStopsList);
            //print logs
            output(s, busStopsList, sortOrder);


        } catch (Exception e) {
            log.error("[run] {}", e.getMessage());
            throw new InvalidValueException("Usage: <station_id> <num_buses> <relative|absolute>");
        }

    }

    public void output(Stop stop, List<BusStops> busStopsList, String sortType) {
        log.info("Stop name is: {}", stop.getStopName());
        log.info("There is: {} in the next 2 hours. \b Next arrivals within the next 2 hours: ", busStopsList.size());
        //added sort for: 3. Add recommendations on how to make this task more challenging
        sortBusArrivingTimes(busStopsList, sortType);
        busStopsList.forEach(aTime -> log.info("Next arrival from route: {}, id: {}  at: {}",aTime.getRouteName(), aTime.getRouteId(), aTime.getArrivingTime()));
    }



}
