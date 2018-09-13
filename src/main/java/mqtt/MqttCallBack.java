package mqtt;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import xbee.MainApp;

public class MqttCallBack implements MqttCallback {
		
	public String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MqttCallBack(String url) {
		
		this.url = url;
	}
	
	public void connectionLost(Throwable throwable) {
		
		MainApp.logger.info("Connection to MQTT broker Lost!");
	}
	
	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

		MainApp.logger.info("Mensagem Recebida:\t - Id: " + s + "| Value: " + new String(mqttMessage.getPayload()));
	    
	}
	
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		
		MainApp.logger.info("Message Delivered :\t - Id: " + iMqttDeliveryToken.getMessageId() + "|  " + iMqttDeliveryToken.getResponse());
	}	  	
  
}
