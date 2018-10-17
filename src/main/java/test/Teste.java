package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.Position;
import service.JsonService;

public class Teste {
	
	public static void populate () {
		
		List<Position> positions = new ArrayList<Position>();
		
		positions.add(new Position("E_GAS", "Sensor01", "NO2", "PPM", 172, new Double(0), new Double(100), new Double(0), new Double(100), new BigDecimal(230340000), new BigDecimal(10000)));		
				
		JsonService js = new JsonService("targetdeviceStatus.json");
		js.save(positions);
		
	}

	public static void update () {
					
		JsonService js = new JsonService("targetdeviceStatus.json");
		js.update(new Position("E_GAS", "Sensor01", "NO2", "PPM", 172, new Double(0), new Double(100), new Double(100), new Double(300), new BigDecimal(382340000), new BigDecimal(20000)));		
	}
	
	public static void read () {
		
		JsonService js = new JsonService("targetdeviceStatus.json");
		List<Position> positions = js.read();
		
		System.out.println("Sensores:: " + positions);
		
	}

}
