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
 * Ene Broker :: 177.144.134.145:8090
 */
public class MainApp {
	
	/* Constants */ 
	private static final String PORT = "COM3";
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
		System.out.println(" |  XBee ENEEYES - PUBLISHER TO MQTT  |");
		System.out.println(" +-----------------------------------------+\n");
		
		if (args.length < 1 || args[0].isEmpty() ) {
			throw new IllegalArgumentException("Você deve especificar: IP/Porta do Broker");
		}
		
		logger.info("Initialing Integrator XBee -> E-Gas");
		
		XBeeDevice myDevice = new XBeeDevice(PORT, BAUD_RATE);
		
		try {
			
			String broker = "tcp://" + args[0] + ":1883";			
					
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
