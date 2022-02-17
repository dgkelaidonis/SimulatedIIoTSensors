# SimulatedIIoTSensors
It provides simulated sensor data for IIoT scenarios.

## How to use
1. Build the project using `maven` and executing the command `mvn clean package`.
2. Run the executable jar from the target directory, by executing the command `java -jar *.jar`.
3. Use your browser/a rest client/curl/etc., and do HTTP-GET on the endpoints below.

## Packed into Docker container
1. Build the project using `maven` and executing the command `mvn clean package`.
2. Build the Docker image of the simulated sensors app, by executing `docker build . -t simulatediiotsenosrs:1.0`
3. Check that image has been created: `docker image ls`
4. Run a container instance: `docker run -d -p 7475:7475 simulatediiotsenosrs:1.0`
5. Check that instance is running properly: `docker container ls`

## Endpoints
Use the following endpoints to get simulated sensor data.
- http://127.0.0.1:7475/simulated-sensors-gw/read/all
- http://127.0.0.1:7475/simulated-sensors-gw/read/all?numOfMachines=250&numOfShopFloors=3
- http://127.0.0.1:7475/simulated-sensors-gw/read/shopfloor?numOfShopFloors=2
- http://127.0.0.1:7475/simulated-sensors-gw/read/equipment?numOfMachines=100

In case that you run the app instance on another host (e.g. on a Docker Container on the cloud) replace the 127.0.0.1 with the public IP of the host.
