package team12.smartstudyplace.mqtt.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import team12.smartstudyplace.mqtt.Mqtt;
import team12.smartstudyplace.mqtt.repository.MqttRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MqttService {
  private final MqttRepository mqttRepository;

  public MqttService(@Lazy MqttRepository mqttRepository) {
    this.mqttRepository = mqttRepository;
  }

  public Mqtt save(Mqtt mqtt) {
    return mqttRepository.save(mqtt);
  }

  public Optional<Mqtt> findById(Long id) {
    return mqttRepository.findById(id);
  }

  public List<Mqtt> findAll() {
    return mqttRepository.findAll();
  }

  public Optional<Mqtt> findLatestMqtt() {
    return mqttRepository.findTopByOrderByIdDesc();
  }

  @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
  public interface MqttGateway {
    String send(@Payload String data);
  }

  public static String getRandomNumber() {
    Random random = new Random();
    double randomNumber = random.nextDouble();
    if (randomNumber < 0.2) {
      return "1";
    } else if (randomNumber < 0.7) {
      return "2";
    } else {
      return "3";
    }
  }
}

