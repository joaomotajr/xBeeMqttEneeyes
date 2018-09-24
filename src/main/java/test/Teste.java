package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.Position;
import service.JsonService;

public class Teste {
	
	public static void populate () {
		
		List<Position> positions = new ArrayList<Position>();
		
		positions.add(new Position("E_GAS", "Sensor01", "NO2", "PPM", 172, new BigDecimal(230340000), new Double(0), new Double(100)));		
		positions.add(new Position("E_GAS", "Sensor02", "CA", "PPM", 173, new BigDecimal(523500000), new Double(0), new Double(1000)));
		positions.add(new Position("E_GAS", "Sensor03", "O", "PPM", 174, new BigDecimal(459344545), new Double(100), new Double(300)));
		positions.add(new Position("E_GAS", "Sensor04", "LEL", "LEL", 175, new BigDecimal(14344545), new Double(15.50), new Double(255)));
		positions.add(new Position("E_GAS", "Sensor05", "O", "PPM", 176, new BigDecimal(99344545), new Double(0), new Double(100)));
		positions.add(new Position("E_GAS", "Sensor06", "O", "PPM", 177, new BigDecimal(19344545), new Double(0), new Double(100)));
		
		JsonService js = new JsonService("targetdeviceStatus.json");
		js.save(positions);
		
	}

	public static void update () {
					
		JsonService js = new JsonService("targetdeviceStatus.json");
		js.update(new Position("E_GAS", "Sensor03", "PPM", "LEL", 174, new BigDecimal(8834001), new Double(100), new Double(300)));		
	}
	
	public static void read () {
		
		JsonService js = new JsonService("targetdeviceStatus.json");
		List<Position> positions = js.read();
		
		System.out.println("Sensores:: " + positions);
		
	}

}
