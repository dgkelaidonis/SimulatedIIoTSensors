package com.microsoft.genesis.iiot.simulatedsensors.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.microsoft.genesis.iiot.simulatedsensors.dto.SimulatedSensorData;

public class DataGenerator {

	/**
	 * Generate dummy data for ambient conditions into a shop floor room/area.
	 * 
	 * @param maxNumberOfSFloors: Defaults to 1, whereas it ranges
	 *                            1<=maxNumberOfMachines<=100
	 * @return A map that includes the correlation between shopFloorId and three
	 *         monitored values {temperature, humidity, luminosity, human presence}
	 */
	private static Map<String, Object> buildAmbientConditionData(Integer maxNumberOfSFloors) {
		/* generate equipment monitoring data */
		Map<String, Object> shopFloorAmbientData = new LinkedHashMap<String, Object>();
		// validate given input.
		maxNumberOfSFloors = (maxNumberOfSFloors == null) ? 1
				: (maxNumberOfSFloors <= 0) ? 1 : (maxNumberOfSFloors > 100) ? 100 : maxNumberOfSFloors;
		int sfc = 1;
		do {
			Map<String, Object> ambientData = new LinkedHashMap<String, Object>();
			ambientData.put("temperature", ThreadLocalRandom.current().nextDouble(18, 30));
			ambientData.put("humidity", ThreadLocalRandom.current().nextDouble(40, 100));
			ambientData.put("luminosity", ThreadLocalRandom.current().nextDouble(0, 200));
			ambientData.put("humanPresence", ((new Random()).nextInt() % 2 != 0) ? "Yes" : "No");
			shopFloorAmbientData.put("sFloor-" + ((sfc < 10) ? "0" : "") + sfc, ambientData);
			sfc++;
		} while (sfc < maxNumberOfSFloors);
		return shopFloorAmbientData;
	}

	/**
	 * Generate dummy data for simulated industry equipment machines.
	 * 
	 * @param maxNumberOfMachines: Defaults to 10, whereas it ranges
	 *                             10<=maxNumberOfMachines<=1000
	 * @return A map that includes the correlation between machineId and three
	 *         monitored values {machine: state, temperature, pressure, vibration}
	 */
	private static Map<String, Object> buildIndustryEquipmentData(Integer maxNumberOfMachines) {
		/* generate equipment monitoring data */
		Map<String, Object> industryEquipment = new LinkedHashMap<String, Object>();
		// validate given input.
		maxNumberOfMachines = (maxNumberOfMachines == null) ? 10
				: (maxNumberOfMachines <= 0) ? 10 : (maxNumberOfMachines > 1000) ? 1000 : maxNumberOfMachines;
		int dc = 1;
		do {
			Map<String, Object> equipmentData = new LinkedHashMap<String, Object>();
			equipmentData.put("state", ((new Random()).nextInt() % 2 != 0) ? "On" : "Off");
			equipmentData.put("temperature", ThreadLocalRandom.current().nextDouble(100, 120));
			equipmentData.put("pressure", ThreadLocalRandom.current().nextDouble(7, 15));
			equipmentData.put("vibration", ThreadLocalRandom.current().nextDouble(0.1, 0.9));
			industryEquipment.put("machine-" + ((dc < 10) ? "0" : "") + dc, equipmentData);
			dc++;
		} while (dc < maxNumberOfMachines);

		return industryEquipment;
	}

	/**
	 * Returns an object that includes data for the ambient (indoor) conditions of
	 * the shop floors of an indystry area (e.g. the main shop floor of a plant).
	 * 
	 * @return SimulatedSensorData object that is being serialized to JSON object,
	 *         that is included into the condition monitoring data payload.
	 */
	public static SimulatedSensorData generateShopFloorAmbientData(Integer maxNumOfShopFloors) {
		SimulatedSensorData sd = new SimulatedSensorData();
		sd.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// add ambient data
		sd.getConditionsData().put("ambient", DataGenerator.buildAmbientConditionData(maxNumOfShopFloors));
		return sd;
	}

	/**
	 * Returns an object that includes dummy data for conditions on (simulated)
	 * machines, that are installed in a shop floor.
	 * 
	 * @return SimulatedSensorData object that is being serialized to JSON object,
	 *         that is included into the condition monitoring data payload.
	 */
	public static SimulatedSensorData generateIndustryEquipmentData(Integer maxNumOfMachines) {
		SimulatedSensorData sd = new SimulatedSensorData();
		sd.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// add machines' data
		sd.getConditionsData().put("industryEquipment", DataGenerator.buildIndustryEquipmentData(maxNumOfMachines));
		return sd;
	}

	/**
	 * Returns an object that includes both ambient (indoor) conditions and active
	 * machines data.
	 * 
	 * @return SimulatedSensorData object that is being serialized to JSON object,
	 *         that is included into the condition monitoring data payload.
	 */
	public static SimulatedSensorData generateAllInOnePackedData(Integer maxNumOfShopFloors, Integer maxNumOfMachines) {
		SimulatedSensorData sd = new SimulatedSensorData();
		sd.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// add ambient data
		sd.getConditionsData().put("ambient", DataGenerator.buildAmbientConditionData(maxNumOfShopFloors));
		// add machines' data
		sd.getConditionsData().put("industryEquipment", DataGenerator.buildIndustryEquipmentData(maxNumOfMachines));
		return sd;
	}

}
