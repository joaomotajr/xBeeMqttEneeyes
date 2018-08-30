package xbee;



import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;

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
		BasicConfigurator.configure();
		 
		System.out.println(" +-----------------------------------------+");
		System.out.println(" |  XBee ENEEYES - PUBLISHER TO MQTT       |");
		System.out.println(" +-----------------------------------------+\n");
		
		if (args.length < 2 || args[0].isEmpty() ) {
			throw new IllegalArgumentException("Você deve especificar: IP:Porta do Broker e Porta COM");
		}
		
		logger.info("Initialing Integrator XBee -> E-Gas");
		
		String broker = "tcp://" + args[0];
		String comPort = args[1];
		
		logger.info("MQTT Broker IP  :: " + broker + " || Virtual Usb Port :: " + comPort);
		
		XBeeDevice myDevice = new XBeeDevice(comPort, BAUD_RATE);
		
		try {			
						
					
			myDevice.open();
			
			MyDataReceiveListener dataReceiveListener = new MyDataReceiveListener();
			dataReceiveListener.setBroker(broker);
			myDevice.addDataListener(dataReceiveListener);
			
			System.out.println("\n>> Waiting for data...");
						
		} catch (XBeeException e) {
			
			logger.error("Sorry, something wrong!", e);
			System.exit(1);
		}
	}
}
