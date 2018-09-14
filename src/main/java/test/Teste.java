package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.Position;
import service.JsonService;

public class Teste {
	
	public static void populate () {
		
		List<Position> positions = new ArrayList<Position>();
		
		positions.add(new Position("E_GAS", "Sensor1", "NO2", 172, new BigDecimal(230340000)));
		positions.add(new Position("E_GAS", "Sensor2", "CA", 173, new BigDecimal(123500000)));
		positions.add(new Position("E_GAS", "Sensor3", "O", 174, new BigDecimal(59344545)));
		
		JsonService js = new JsonService("deviceStatus.json");
		js.save(positions);
		
	}

	public static void update () {
					
		JsonService js = new JsonService("deviceStatus.json");
		js.update(new Position("E_GAS", "Sensor4", "O", 174, new BigDecimal(8834001)));		
	}
	
	public static void read () {
		
		JsonService js = new JsonService("deviceStatus.json");
		List<Position> positions = js.read();
		
		System.out.println("Sensores:: " + positions);
		
	}

}
