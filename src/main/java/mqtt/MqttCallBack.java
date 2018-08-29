package mqtt;


import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttCallBack implements MqttCallback {
		
	final static Logger logger = Logger.getLogger(MqttCallBack.class);

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
		
		logger.info("Connection to MQTT broker Lost!");
	}
	
	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

		logger.info("Mensagem Recebida:\t - Id: " + s + "| Value: " + new String(mqttMessage.getPayload()));
	    
	}
	
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		
		logger.info("Message Delivered :\t - Id: " + iMqttDeliveryToken.getMessageId() + "|  " + iMqttDeliveryToken.getResponse());
	}	  	
  
}
