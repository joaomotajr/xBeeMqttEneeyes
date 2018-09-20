package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import xbee.MainApp;

public class Publisher {
	
	public Publisher() {
		
	}

	public static void send(String broker, String id, String value) throws MqttException {
		
		MainApp.logger.info("Publishing => Broker :: " + broker + " - id/value => " + id + "/" + value);
		  	                    	  
		MqttClient client = new MqttClient(broker , MqttClient.generateClientId());    
		client.connect();		  
		
		MqttMessage message = new MqttMessage();
		message.setPayload(value.getBytes());    
		client.publish(id, message);
		client.disconnect();	

	}
  
  	public static Boolean test(String broker) {
  		 
  		
	  	try { 
	  		MainApp.logger.info("TESTING... Publisher => Broker :: " + broker );
	    	  
	  		MqttClient client = new MqttClient(broker , MqttClient.generateClientId());
	  		
	  		if(!client.isConnected())
	  			client.connect();
	  	  
	  		MainApp.logger.info("Publisher Broker Test :: Successfull" );	  		
			
		} catch (Exception e) {
			MainApp.logger.error("END Test Publisher - Failed :: " + e);
			return false;
		}
	  	
	  	return true;
  	}  

  	
}
