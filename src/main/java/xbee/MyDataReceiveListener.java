package xbee;




import java.math.BigDecimal;

import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.models.XBeeMessage;

import model.Position;
import mqtt.Publisher;
import service.JsonService;

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
	/*
	 * (non-Javadoc)
	 * @see com.digi.xbee.api.listeners.IDataReceiveListener#dataReceived(com.digi.xbee.api.models.XBeeMessage)
	 */
	
	private String broker;		
	JsonService js = new JsonService("deviceStatus.json");

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public void dataReceived(XBeeMessage xbeeMessage) {
		
		String messageReceived = xbeeMessage.getDataString();
		XBee64BitAddress macAddress = xbeeMessage.getDevice().get64BitAddress();
		
		String value[] = messageReceived.split("\n");
		
		if(value.length > 1 ) {
			MainApp.logger.error("Erro de configuração de Sensores, verifique ::" + value);
			return;
		}
				
		value = messageReceived.split(";");
		
		if(!value[0].contentEquals("EGAS"))
			return;

		try {
			String id = value[2];
			String valor = value[3];
			
			Publisher.Execute(broker, id, valor);
			js.update(new Position("E_GAS", "Sensor4", "O", Integer.parseInt(id), new BigDecimal(valor)));
			
			
		} catch (Exception e) {
			MainApp.logger.error(e);
		}
		
		MainApp.logger.info(String.format("From %s >> %s ", macAddress, messageReceived));
	}

}
