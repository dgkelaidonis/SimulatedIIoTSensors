package com.microsoft.genesis.iiot.simulatedsensors.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.genesis.iiot.simulatedsensors.dto.SimulatedSensorData;
import com.microsoft.genesis.iiot.simulatedsensors.utils.DataGenerator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/simulated-sensors-gw")
@Api(value = "API to generate simulated sensor telemetry data.", description = "This API provides the capability to get simulated sensor telemetry data in JSON format, for supporting experimentation purposes for your applications.", produces = "application/json")
public class SensorsGwController {

	/**
	 * This method serves an HTTP-GET request on a URL in form
	 * /read?sensorMeasurement=${sensor-name}. If the variable 'sensorMeasurement'
	 * has the value 'all', it return all connected GW sensors measurements.
	 * 
	 * @param dataType        set to 'all' to fetch the sensor data from all sensors
	 *                        that are connected on the GW.
	 * @param numOfShopFloors the number of the simulated rooms (shop floors) to
	 *                        monitor their ambient condition monitoring, in terms
	 *                        of temperature, humidity, luminosity.
	 * @param numOfMachines   the number of the simulated industry machines to
	 *                        monitor their conditions, in terms of temperature,
	 *                        pressure, vibration.
	 * 
	 * @return HTTP Response that packs a JSON object that includes the sensor(s)'
	 *         data.
	 */
	@ApiOperation(value = "Get simulated telemetry data.", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "JSON data with telemetry measurements", response = SimulatedSensorData.class),
			@ApiResponse(code = 500, message = "Internal error") })
	@RequestMapping(value = "/read/{dataType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchConditionMonitoringData(
			@ApiParam(value = "The type of the simulated telemetry data.", allowableValues = "all,shopfloor,equipment", example = "all", required = true) @NonNull @PathVariable(value = "dataType", required = true) String dataType,
			@ApiParam(value = "The number of the simulated rooms/spaces, in the shop floor. If it is empty, it will default to 1 simulated shop floor.", example = "1") @RequestParam(value = "numOfShopFloors", required = false) String numOfShopFloors,
			@ApiParam(value = "The number of the simulated machines. If it is empty, it will default to 10 simulated machines.", example = "10") @RequestParam(value = "numOfMachines", required = false) String numOfMachines) {
		try {
			if (dataType.equalsIgnoreCase("all")) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(DataGenerator.generateAllInOnePackedData(
								(numOfShopFloors != null) ? Integer.parseInt(numOfShopFloors) : null,
								(numOfMachines != null) ? Integer.parseInt(numOfMachines) : null));
			} else if (dataType.equalsIgnoreCase("equipment")) {
				return ResponseEntity.status(HttpStatus.OK).body(DataGenerator.generateIndustryEquipmentData(
						(numOfMachines != null) ? Integer.parseInt(numOfMachines) : null));
			} else if (dataType.equalsIgnoreCase("shopfloor")) {
				return ResponseEntity.status(HttpStatus.OK).body(DataGenerator.generateShopFloorAmbientData(
						(numOfShopFloors != null) ? Integer.parseInt(numOfShopFloors) : null));
			} else {
				throw new Exception("Unsupported telemetry data.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}