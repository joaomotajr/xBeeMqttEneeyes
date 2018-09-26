xBeeMqttEneeyes

#call on command line windows
java -Dlog4j.configuration=file:///%CD%\cfg\log4j.properties -jar xBeeMqttEneeyes-0.0.1-SNAPSHOT-jar-with-dependencies.jar 177.144.134.145 com1


#call on command line linux
 java -Dlog4j.configuration=file:.//$currDir/cfg/log4j.properties  -jar xBeeMqttEneeyes-0.0.1-SNAPSHOT-jar-with-dependencies.jar 177.144.134.145 /dev/ttyUSB0


#MQTT Online Broker testes
tcp://iot.eclipse.org:1883


[{"tipo":"O","minValue":0.0,"maxValue":100.0,"nome":"SENSOR 02","unidade":"PPM","id":117,"value":976,"key":"EGAS"},
{"tipo":"NO2","minValue":0.0,"maxValue":100.0,"nome":"SENSOR 01","unidade":"LEL%","id":116,"value":78125,"key":"EGAS"}]