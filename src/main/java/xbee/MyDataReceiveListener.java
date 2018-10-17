package xbee;

import java.math.BigDecimal;

import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.models.XBeeMessage;

import mqtt.Publisher;
import service.UpdateDeviceFactory;

/**
 * Class to manage the XBee received data that was sent by other modules in the 
 * same network.
 * 
 * <p>Acts as a data listener by implementing the 
 * {@link IDataReceiveListener} interface, and is notified when new 
 * data for the module is received.</p>
 * 
 * @see IDataReceiveListener
 *
 */
public class MyDataReceiveListener implements IDataReceiveListener {
	
	private String broker;		
	private String sinc;
//	JsonPositionService js = new JsonPositionService("targetdeviceStatus.json");

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}	

	public String getSinc() {
		return sinc;
	}

	public void setSinc(String sinc) {
		this.sinc = sinc;
	}

	public void dataReceived(XBeeMessage xbeeMessage) {
		
		String messageReceived = xbeeMessage.getDataString();
		XBee64BitAddress macAddress = xbeeMessage.getDevice().get64BitAddress();
		
		String value[] = messageReceived.split("\n");
		
		if(value.length > 1 ) {
			MainApp.logger.error("Erro de configuração de Sensores, verifique ::" + value);
			return;
		}		
		
		messageReceived = messageReceived.replaceAll("\r", "").replaceAll("\n", "");
		System.getProperty("line.terminator");
		value = messageReceived.split(";");
		
		if(!value[0].contentEquals("EGAS"))
			return;

		MainApp.logger.info(String.format("MESSAGE Received From MCAdress %s  >> %s ", macAddress, messageReceived));
		
		try {
//			String nome = value[1];
//			String tipo = value[2];			
//			String unidade = value[3];
//			String id = value[4];			
//			String min = value[5];
//			String max = value[6];
//			String alarm1 = value[7];
//			String alarm2 = value[8];
//			String valor = value[9];
//			String milliTime = value[10];
			
			String nome = value[1];			
			String id = value[4];			
			String valor = value[9];
			String milliTime = value[10];			
			
			if (sinc.equals("mqtt"))
				Publisher.send(broker, id, valor);
			
//			Position position = new Position();
//			position.setKey("E_GAS");
//			position.setNome(nome);
//			position.setId(Integer.parseInt(id));
//			position.setTipo(tipo);
//			position.setUnidade(unidade);
//			position.setValue(new BigDecimal(Double.parseDouble(valor)));
//			position.setMinValue(Double.parseDouble(min));
//			position.setMaxValue(Double.parseDouble(max));
//			position.setAlarm1(Double.parseDouble(alarm1));
//			position.setAlarm2(Double.parseDouble(alarm2));
//			position.setMilliTime(new BigDecimal(milliTime));			
//			js.update(position);
			
			UpdateDeviceFactory uds = new UpdateDeviceFactory();
			uds.updatePosition("E_GAS", nome, Integer.parseInt(id), new BigDecimal(Double.parseDouble(valor)), new BigDecimal(milliTime));		
			
		} catch (Exception e) {
			MainApp.logger.error(e);
		}	
		
	}

}
