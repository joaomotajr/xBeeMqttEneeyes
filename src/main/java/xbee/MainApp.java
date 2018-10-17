package xbee;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;

import model.Config;
import mqtt.Publisher;
import service.UpdateDeviceFactory;
import test.Teste;
/**
 * XBee Java Library Receive Data sample application.
 * 
 * <p>This example registers a listener to manage the received data.</p>
 * 
 * <p>For a complete description on the example, refer to the 'ReadMe.txt' file
 * included in the root directory.</p>
 * Ene Broker :: 177.144.134.145:1883
 */
public class MainApp {
	
	/* Constants */	
	private static final int BAUD_RATE = 9600;
	
	public static final Logger logger = LogManager.getLogger(MainApp.class);
	
	/**
	 * Application main method.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
				
		logger.info("Initialing Integrator XBee -> E-Gas (MQTT-Server)");
		
//		UpdateDeviceFactory uds = new UpdateDeviceFactory();
//		Config config = uds.getConfig(120);						
//		Teste.populate();
//		Teste.update();
//		Teste.testeConfig();		
		 
		System.out.println(" +-----------------------------------------+");
		System.out.println(" |  XBee ENEEYES - PUBLISHER TO MQTT       |");
		System.out.println(" +-----------------------------------------+\n");
		
		if (args.length != 3) {
			logger.error("Você deve especificar: Integração[none, mqtt],  IP:Porta do Broker e Porta COM do xBee");			
			System.exit(1);
		}				
		
		String sinc = args[0].toLowerCase();
		String broker = "tcp://" + args[1];		
		String comPort = args[2];
		
		if(sinc.equals("none"))		
			logger.info("Integração com E-Gas Desabilitada..");
		else if(sinc.equals("mqtt"))
			logger.info("MQTT Broker IP  :: " + broker + " ||  Virtual Usb Port / TX :: " + comPort + "/" + BAUD_RATE);		
		else 
			logger.info("Parâmetro Integração Inválido");
				
		try {

			if (Publisher.test(broker)) {
						
				logger.info("Checando xBee :: Virtual Usb Port / TX :: " + comPort + "/" + BAUD_RATE);
				XBeeDevice myDevice = new XBeeDevice(comPort, BAUD_RATE);
				myDevice.open();
				
				logger.info("XBee :: Virtual Usb Port / TX :: " + comPort + "/" + BAUD_RATE + " OK");
				
				MyDataReceiveListener dataReceiveListener = new MyDataReceiveListener();
				dataReceiveListener.setBroker(broker);
				dataReceiveListener.setSinc(sinc);
				myDevice.addDataListener(dataReceiveListener);
				
				System.out.println("\n>> Esperando por Dados dos Routers...");
			}
						
		} catch (XBeeException e) {
			
			logger.error("Ops! Há Algo errado, verifique\n", e);
			System.exit(1);
		}
	}
}
