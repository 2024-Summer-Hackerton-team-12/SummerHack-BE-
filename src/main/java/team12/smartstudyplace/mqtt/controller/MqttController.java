package team12.smartstudyplace.mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import team12.smartstudyplace.mqtt.Mqtt;
import team12.smartstudyplace.mqtt.service.MqttService;

import java.util.Optional;

@RestController
public class MqttController {

  private final MqttService mqttService;

  @Autowired
  public MqttController(@Lazy MqttService mqttService) {
    this.mqttService = mqttService;
  }

  @GetMapping("/api/mqtt")
  public Optional<Mqtt> findLatestId() {
    Optional<Mqtt> latestMqtt = mqttService.findLatestMqtt();
    return latestMqtt;
  }
}
