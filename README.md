# Java Application

Application return buses arriving times within the next 2 hours from the time of the query. 


### Usage 
Download the lastest jar file.
Place in directory.

And run with: 
```
java -jar bus-trips.jar 1 5 relative
```
For help use command `--help` at the end.

Configuration parameters:

| Parameter            | Example Value | Required | Description                                            |
|----------------------|---------------|----------|--------------------------------------------------------|
| `station_id`         | `1`           | yes      | Id of bus station.                                     |
| `num_buses_per_line` | `5`           | yes      | Number of arriving times to show.                      |
| `time`               | `absolute`    | yes      | Type of arriving time. ("relative", "absolute")          |
| `sortType`           | `asc`         | no       | Type of sorting  ascending or descending ("asc,"desc") |

### RESPONSE example:
```
2026-02-17T07:44:37.712+01:00  INFO 48892 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Stop name is: Uhud battlefield
2026-02-17T07:44:37.715+01:00  INFO 48892 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : There is: 5 in the next 2 hours. Next arrivals within the next 2 hours: 
2026-02-17T07:44:37.716+01:00  INFO 48892 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Next arrival from route: Short name, id: 101  at: 08:57:36
2026-02-17T07:44:37.716+01:00  INFO 48892 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Next arrival from route: Short name, id: 105  at: 08:40
2026-02-17T07:44:37.716+01:00  INFO 48892 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Next arrival from route: Short name, id: 105  at: 08:26:08
2026-02-17T07:44:37.716+01:00  INFO 48892 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Next arrival from route: Short name, id: 105  at: 08:10
2026-02-17T07:44:37.716+01:00  INFO 48892 --- [busTrips] [           main] busTrips.busTrips.runner.InitialRunner   : Next arrival from route: Short name, id: 101  at: 07:50
```


### 3. Add recommendations on how to make this task more challenging
 Added search by type of ascending or descending time
