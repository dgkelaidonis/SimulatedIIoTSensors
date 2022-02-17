package com.microsoft.genesis.iiot.simulatedsensors.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The structure of the telemetry data response.")
public class SimulatedSensorData {
	@ApiModelProperty(example = "{\"conditionsData\":{\"ambient\":{\"sFloor-01\":{\"temperature\":24.62625178200765,\"humidity\":53.31754484970623,\"luminosity\":161.54185488068126,\"humanPresence\":\"No\"}},\"industryEquipment\":{\"machine-01\":{\"state\":\"On\",\"temperature\":108.16995174655405,\"pressure\":9.164237698725291,\"vibration\":0.7242233062664358},\"machine-02\":{\"state\":\"On\",\"temperature\":110.72459959739783,\"pressure\":12.353141902171107,\"vibration\":0.2558224762174202},\"machine-03\":{\"state\":\"On\",\"temperature\":106.96488074014465,\"pressure\":10.535030908635026,\"vibration\":0.5289507227297233},\"machine-04\":{\"state\":\"Off\",\"temperature\":105.62881275663949,\"pressure\":8.568943211004505,\"vibration\":0.6054827270057157},\"machine-05\":{\"state\":\"Off\",\"temperature\":117.93403917262748,\"pressure\":12.020202463176858,\"vibration\":0.3049795386078749},\"machine-06\":{\"state\":\"On\",\"temperature\":103.61037967672803,\"pressure\":11.050536380657746,\"vibration\":0.3123735267581263},\"machine-07\":{\"state\":\"Off\",\"temperature\":105.81937680524308,\"pressure\":7.7699839211166415,\"vibration\":0.7795792671272496},\"machine-08\":{\"state\":\"Off\",\"temperature\":109.86594855887178,\"pressure\":9.455649920958447,\"vibration\":0.7212152663062161},\"machine-09\":{\"state\":\"On\",\"temperature\":117.32301029790963,\"pressure\":11.475495150717762,\"vibration\":0.16130706343231385}}}}")
	public Map<String, Map<String, Object>> conditionsData = new LinkedHashMap<String, Map<String, Object>>();
	@ApiModelProperty(example="\"2022-02-16 13:45:22\"")
	public String timestamp;

	public Map<String, Map<String, Object>> getConditionsData() {
		return conditionsData;
	}

	public void setConditionsData(Map<String, Map<String, Object>> conditionsData) {
		this.conditionsData = conditionsData;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}