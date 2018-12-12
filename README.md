xBeeMqttEneeyes

#call on command line windows
java -Dlog4j.configuration=file:///%CD%\cfg\log4j.properties -jar xBeeMqttEneeyes-0.0.1-SNAPSHOT-jar-with-dependencies.jar mqtt 177.144.134.145 COM1


#call on command line linux
 java -Dlog4j.configuration=file:.//$currDir/cfg/log4j.properties  -jar xBeeMqttEneeyes-0.0.1-SNAPSHOT-jar-with-dependencies.jar 177.144.134.145 /dev/ttyUSB0


#MQTT Online Broker testes
tcp://iot.eclipse.org:1883


[{"tipo":"O","minValue":0.0,"maxValue":100.0,"nome":"SENSOR 02","unidade":"PPM","id":117,"value":976,"key":"EGAS"},
{"tipo":"NO2","minValue":0.0,"maxValue":100.0,"nome":"SENSOR 01","unidade":"LEL%","id":116,"value":78125,"key":"EGAS"}]

#Configuração Exemplo
[{"id":116,"detail":"Sala 1 | Mesa1","minValue":0,"maxValue":100,"alarm1":39,"alarm2":60},{"id":117,"detail":"Sala 2 | Parede","minValue":0,"maxValue":100,"alarm1":39,"alarm2":60},{"id":118,"detail":"Sala 2 | Relógio","minValue":0,"maxValue":100,"alarm1":39,"alarm2":60}]


--command line - windows
nssm install ene-receiver "C:\Program Files\Java\jdk1.8.0_191\bin\java.exe" "-Dlog4j.configuration=file:///%CD%\cfg\log4j.properties -jar C:\dashboard\sistema\xBeeMqttEneeyes-0.0.1-SNAPSHOT-jar-with-dependencies.jar mqtt 177.144.134.145 COM4"

--instalar serviço no windows
-- nssm no mesmo diretorio
-- Arquvio start.Bat

-Batch file - start.bat
@ECHO OFF
REM start.bat  code
java -jar -Dlog4j.configuration=file:///%CD%\cfg\log4j.properties xBeeMqttEneeyes-0.0.1-SNAPSHOT-jar-with-dependencies.jar %1 %2 %3

nssm install ene-receiver

|-----------------------------------------------
| path:: C:\dashboard\sistema\start.bat
| args:: mqtt 177.144.134.145 COM4
| startup directory:: C:\dashboard\sistema
| Details:: Ene xBee Data-Receiver

--remover
nssm remove ene-receiver

--editar parametros
nssm edit ene-receiver