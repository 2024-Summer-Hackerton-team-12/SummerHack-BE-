package team12.smartstudyplace.mqtt.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import team12.smartstudyplace.mqtt.Mqtt;
import team12.smartstudyplace.mqtt.presentation.dto.MqttResponse;

import java.util.List;

@Service
public class MqttService {
  private final MqttService mqttService;

  public MqttService(@Lazy MqttService mqttService) {
    this.mqttService = mqttService;
  }

  public Mqtt save(Mqtt mqtt) {
    return mqttService.save(mqtt);
  }

  public List<Mqtt> findByClientId(String clientId) {
    return mqttService.findByClientId(clientId);
  }

  public MqttResponse findAll() {
    return mqttService.findAll();
  }

  @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
  interface MqttGateway {
    void sent(@Payload String data);
  }
}

