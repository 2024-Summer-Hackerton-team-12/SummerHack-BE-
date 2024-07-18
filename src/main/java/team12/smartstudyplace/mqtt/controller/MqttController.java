package team12.smartstudyplace.mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;
import team12.smartstudyplace.mqtt.Mqtt;
import team12.smartstudyplace.mqtt.presentation.dto.MqttRequest;
import team12.smartstudyplace.mqtt.presentation.dto.MqttResponse;
import team12.smartstudyplace.mqtt.service.MqttService;

@RestController
public class MqttController {

  private final MqttService mqttService;

  @Autowired
  public MqttController(@Lazy MqttService mqttService) {
    this.mqttService = mqttService;
  }

  @GetMapping
  public MqttResponse findAll() {
    return mqttService.findAll();
  }

//  @PostMapping("bssm/hino/data")
//  public Mqtt publishToMqtt(@RequestBody MqttRequest request) {
//    System.out.println("request = " + request);
//    Mqtt mqtt = new Mqtt();
//    mqtt.setClientId("GSB"); // Assuming SUB_CLIENT_ID is "GSB"
//    mqtt.setTemperature(request.temperature());
//    mqtt.setHumidity(request.humidity());
//    mqtt.setLedStatus(request.ledStatus());
//    return mqttService.save(mqtt);
//  }

  // Assuming there's a MessageHandler bean defined in the application context
//  @ServiceActivator(inputChannel = "mqttInboundChannel")
//  public MessageHandler mqttInbound() {
//    return new MessageHandler() {
//      @Override
//      public void handleMessage(Message<?> message) throws MessagingException {
//        String payload = message.getPayload().toString();
//        System.out.println("Received MQTT message: " + payload);
//
//        // Parse the payload if necessary and create Mqtt object
//        // Assuming the payload format is "Humidity: xx.xx % Temperature: xx.xx *C LED: xxxxx"
//        String[] data = payload.split(" ");
//        float humidity = Float.parseFloat(data[1]);
//        float temperature = Float.parseFloat(data[4]);
//        String ledStatus = data[7];
//
//        Mqtt mqtt = new Mqtt();
//        mqtt.setClientId("GSB"); // Assuming SUB_CLIENT_ID is "GSB"
//        mqtt.setHumidity(humidity);
//        mqtt.setTemperature(temperature);
//        mqtt.setLedStatus(ledStatus);
//        mqttService.save(mqtt);
//      }
//    };
//  }
}
