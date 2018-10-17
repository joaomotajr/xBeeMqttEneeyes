package service;

import java.math.BigDecimal;
import java.util.List;

import model.Config;
import model.Position;

public class UpdateDeviceFactory {
		
	JsonPositionService jsPosition = new JsonPositionService("targetdeviceStatus.json");
	JsonConfigService jsConfig = new JsonConfigService("configdevice.json");
	
	public UpdateDeviceFactory() {		
	}
	
	public Config getConfig(int id) {
		
		Config config = new Config();
		config.setId(id);
				
		List<Config> configs = jsConfig.read();
		
		if(configs.contains(config)) {
			config = configs.get(configs.indexOf(config));
		}	
						
		return config;
	}
	
	public void updatePosition(String key, String nome, int id, BigDecimal value, BigDecimal milliTime) {		
		
		Config config = new Config();
		config = getConfig(id);
		
		Position position = new Position();
		position.setKey(key);
		position.setNome(nome);
		position.setId(id);
		position.setTipo(config.getTipo());
		position.setUnidade(config.getUnidade());
		position.setValue(value);
		position.setMinValue(config.getMinValue());
		position.setMaxValue(config.getMaxValue());
		position.setAlarm1(config.getAlarm1());
		position.setAlarm2(config.getAlarm2());
		position.setMilliTime(milliTime);
		
		jsPosition.update(position);
		
	}
	
}
