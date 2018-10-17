package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.Position;
import service.JsonPositionService;
import service.UpdateDeviceFactory;

public class Teste {
	
	public static void populate () {
		
		List<Position> positions = new ArrayList<Position>();
		
		positions.add(new Position("E_GAS", "Sensor01", "CO", "PPM", "Sala 1 - Mesa", 116, new Double(0), new Double(100), new Double(39), new Double(60), new BigDecimal(2303400), new BigDecimal(10000)));
		positions.add(new Position("E_GAS", "Sensor02", "CO", "PPM", "Sala 1 - Parede", 117, new Double(0), new Double(100), new Double(39), new Double(60), new BigDecimal(1003400), new BigDecimal(10000)));	
		positions.add(new Position("E_GAS", "Sensor03", "CO", "PPM", "Sala 2", 118, new Double(0), new Double(100), new Double(39), new Double(60), new BigDecimal(053400), new BigDecimal(10000)));
				
		JsonPositionService js = new JsonPositionService("targetdeviceStatus.json");
		js.save(positions);
		
	}

	public static void update () {
					
		JsonPositionService js = new JsonPositionService("targetdeviceStatus.json");
		js.update(new Position("E_GAS", "Sensor01", "NO", "PPM", "Sala 1 - Mesa", 116, new Double(0), new Double(100), new Double(39), new Double(60), new BigDecimal(3223400), new BigDecimal(20000)));		
	}
	
	public static void read () {
		
		JsonPositionService js = new JsonPositionService("targetdeviceStatus.json");
		List<Position> positions = js.read();
		
		System.out.println("Sensores:: " + positions);
		
	}
	
	public static void testeConfig() {
		UpdateDeviceFactory uds = new UpdateDeviceFactory();
		uds.updatePosition("E_GAS", "Sensor01", 111, new BigDecimal(2353400), new BigDecimal(10000));
	}

}
