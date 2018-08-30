package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import xbee.MainApp;

public class Publisher {


  public static void Execute(String broker, String id, String value) throws MqttException {
    
	  MainApp.logger.info("START Publisher => => Broker :: " + broker );
	  
	  MqttClient client = new MqttClient(broker, MqttClient.generateClientId());    
//	  client.setCallback( new MqttCallBack("127.0.0.1") );
	  client.connect();
    
	  MqttMessage message = new MqttMessage();
	  message.setPayload(value.getBytes());    
	  client.publish(id, message);
	  client.disconnect();
	  
	  MainApp.logger.info("END Publisher." );

  	}
}
