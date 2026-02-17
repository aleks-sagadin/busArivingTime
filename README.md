# Java Application

Application return buses arriving times within the next 2 hours from the time of the query. 


### Usage 
Download the lastest jar file.
Place in directory.

And run with: 
```
java -jar bus-trips.jar 1 5 relative
```
For help use command `--help`.

Configuration parameters:

| Parameter            | Example Value | Required | Description                                            |
|----------------------|---------------|----------|--------------------------------------------------------|
| `station_id`         | `1`           | yes      | Id of bus station.                                     |
| `num_buses_per_line` | `5`           | yes      | Number of arriving times to show.                      |
| `time`               | `absolute`    | yes      | Type of arriving time. (relative, "absolute")          |
| `sortType`           | `asc`         | no       | Type of sorting  ascending or descending ("asc,"desc") |

### RESPONSE example:
```
2026-02-16T20:43:58.788+01:00  INFO 1976 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Stop name is: Uhud battlefield
2026-02-16T20:43:58.793+01:00  INFO 1976 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : There is: 5 in the next 2 hours. Next arrivals within the next 2 hours: 
2026-02-16T20:43:58.795+01:00  INFO 1976 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Next arrival from route: 101  at: 22:17:38
2026-02-16T20:43:58.795+01:00  INFO 1976 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Next arrival from route: 105  at: 21:14:24
2026-02-16T20:43:58.795+01:00  INFO 1976 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Next arrival from route: 101  at: 20:58:05
2026-02-16T20:43:58.795+01:00  INFO 1976 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Next arrival from route: 101  at: 20:55
2026-02-16T20:43:58.795+01:00  INFO 1976 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Next arrival from route: 101  at: 20:45
```


### 3. Add recommendations on how to make this task more challenging
 Added search by type of ascending or descending time
