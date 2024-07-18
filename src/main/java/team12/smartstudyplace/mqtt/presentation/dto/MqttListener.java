package team12.smartstudyplace.mqtt.presentation.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import team12.smartstudyplace.mqtt.service.MqttService;
import team12.smartstudyplace.mqtt.Mqtt;

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
    try {
      System.out.println("[" + LocalDateTime.now() + "] Received message: " + message.getPayload());
      String payload = (String) message.getPayload();
      Mqtt mqttData = parsePayload(payload);

      if (mqttData != null) {
        mqttService.save(mqttData); // 새로운 데이터를 저장
      } else {
        System.err.println("Failed to parse message: " + payload);
      }
    } catch (Exception e) {
      System.err.println("Error handling message: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private Mqtt parsePayload(String payload) {
    try {
      // Example: Humidity: 48.00 % Temperature: 29.30 *C LED: Medi
      String[] parts = payload.split(" ");
      double humidity = Double.parseDouble(parts[1]);
      double temperature = Double.parseDouble(parts[4]);
      String ledStatus = parts.length > 7 ? "" : parts[7];

      Mqtt mqttData = new Mqtt();
      mqttData.setHumidity(humidity);
      mqttData.setTemperature(temperature);
      mqttData.setLedStatus(ledStatus);

      return mqttData;
    } catch (Exception e) {
      System.err.println("Error parsing payload: " + e.getMessage());
      return null;
    }
  }
}
