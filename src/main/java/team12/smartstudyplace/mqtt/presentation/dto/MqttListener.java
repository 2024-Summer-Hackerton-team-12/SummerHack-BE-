package team12.smartstudyplace.mqtt.presentation.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import team12.smartstudyplace.mqtt.Mqtt;
import team12.smartstudyplace.mqtt.service.MqttService;

import java.time.LocalDateTime;

@Component
public class MqttListener implements MessageHandler {

  private final MqttService mqttService;

  @Autowired
  public MqttListener(MqttService mqttService) {
    this.mqttService = mqttService;
  }

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {
    String payload = message.getPayload().toString();
    System.out.println("[" + LocalDateTime.now() + "] Received message: " + payload);

    // Split payload by spaces
    String[] dataParts = payload.split("\\s+");

    // Initialize variables
    String clientId = null;
    double humidity = 0.0;
    double temperature = 0.0;
    String ledStatus = null;

    // Iterate through data parts to find and parse values
    for (String part : dataParts) {
      if (part.startsWith("clientId:")) {
        clientId = part.substring("clientId:".length());
      } else if (part.startsWith("humidity:")) {
        humidity = Double.parseDouble(part.substring("humidity:".length()));
      } else if (part.startsWith("temperature:")) {
        temperature = Double.parseDouble(part.substring("temperature:".length()));
      } else if (part.startsWith("ledStatus:")) {
        ledStatus = part.substring("ledStatus:".length());
      }
    }

    // Create Mqtt object and save to database
    Mqtt mqtt = new Mqtt();
    mqtt.setClientId(clientId);
    mqtt.setHumidity(humidity);
    mqtt.setTemperature(temperature);
    mqtt.setLedStatus(ledStatus);

    mqttService.save(mqtt);
  }
}
