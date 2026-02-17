package busTrips.busTrips;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class BusTripsApplication{

	public static void main(String[] args) {
        if (Arrays.asList(args).contains("--help")) {
            log.info("[help] Application arguments: busTrips <station_id> <num_buses_per_line> <relative|absolute> <asc|desc>");
            log.info("Example:");
            log.info("[help]   --station_id=1          # ID of the stop (e.g., 12345)");
            log.info("[help]   --num_buses_per_line=10 #  The maximum number of upcoming arrivals to show per route.");
            log.info("[help]   --relative|absolute  # Relative of absolute time");
            log.info("[help]   --desc|asc  # For sorting arriving time  "); //this is additional
            log.info("[help] Run example: java -jar bus-trips.jar 3 5 relative asc");
            return;
        }
		SpringApplication.run(BusTripsApplication.class, args);
	}

}
