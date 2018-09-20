package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.Position;
import service.JsonService;

public class Teste {
	
	public static void populate () {
		
		List<Position> positions = new ArrayList<Position>();
		
		positions.add(new Position("E_GAS", "Sensor1", "NO2", "", 172, new BigDecimal(230340000), new Double(0), new Double(100)));		
		positions.add(new Position("E_GAS", "Sensor2", "CA", "", 173, new BigDecimal(523500000), new Double(0), new Double(1000)));
		positions.add(new Position("E_GAS", "Sensor3", "O", "", 174, new BigDecimal(459344545), new Double(100), new Double(300)));
		positions.add(new Position("E_GAS", "Sensor3", "LEL", "", 175, new BigDecimal(14344545), new Double(15.50), new Double(255)));
		positions.add(new Position("E_GAS", "Sensor3", "O", "", 176, new BigDecimal(99344545), new Double(0), new Double(100)));
		
		JsonService js = new JsonService("deviceStatus.json");
		js.save(positions);
		
	}

	public static void update () {
					
		JsonService js = new JsonService("deviceStatus.json");
		js.update(new Position("E_GAS", "Sensor4", "O", "LEL", 174, new BigDecimal(8834001), new Double(100), new Double(300)));		
	}
	
	public static void read () {
		
		JsonService js = new JsonService("deviceStatus.json");
		List<Position> positions = js.read();
		
		System.out.println("Sensores:: " + positions);
		
	}

}
