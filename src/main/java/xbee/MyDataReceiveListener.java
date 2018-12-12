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
 * <p>
 * Acts as a data listener by implementing the {@link IDataReceiveListener}
 * interface, and is notified when new data for the module is received.
 * </p>
 * 
 * @see IDataReceiveListener
 *
 */
public class MyDataReceiveListener implements IDataReceiveListener {

	private String broker;
	private String sinc;

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

		if (value.length > 1) {
			MainApp.logger.error("Erro de configuração de Sensores (1), verifique ::" + messageReceived);
			return;
		}

		try {
			messageReceived = messageReceived.replaceAll("\r", "").replaceAll("\n", "");
			System.getProperty("line.terminator");
			value = messageReceived.split(";");		
		} catch (Exception e) {
			MainApp.logger.error("Erro de configuração de Sensores (2), verifique ::" + messageReceived);
			return;
		}
		
		if (!value[0].contentEquals("EGAS")) {
			MainApp.logger.error("Erro de configuração de Sensores (3), verifique ::" + messageReceived);
			return;
		}

		MainApp.logger.info(String.format("MESSAGE Received From MCAdress %s  >> %s ", macAddress, messageReceived));

		try {
			String nome = value[1];
			String id = value[2];
			String valor = value[3];
			String milliTime = value[4];
			
			Double checkValue = Double.parseDouble(valor);
			
			if(checkValue < 0.0) {
				valor = "0";
			}
			else if ((id.equals("123") || id.equals("126")) && checkValue > 10) {
				checkValue /=5;
				valor = checkValue.toString();
			}
			else if(id.equals("136") && checkValue > 10) {
				checkValue /=1.3;
				valor = checkValue.toString();
			}
			
			if (sinc.equals("mqtt"))
				Publisher.send(broker, id, valor);

			UpdateDeviceFactory uds = new UpdateDeviceFactory();
			uds.updatePosition("E_GAS", nome, Integer.parseInt(id), new BigDecimal(Double.parseDouble(valor)),
					new BigDecimal(milliTime));

		} catch (Exception e) {
			MainApp.logger.error(e);
		}

	}

}
