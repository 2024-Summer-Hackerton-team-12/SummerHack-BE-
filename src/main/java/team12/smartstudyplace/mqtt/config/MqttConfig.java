package team12.smartstudyplace.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import team12.smartstudyplace.mqtt.presentation.dto.MqttListener;
import team12.smartstudyplace.mqtt.service.MqttService;


@Configuration
public class MqttConfig {

  private final MqttService mqttService;

  private static final String PUB_CLIENT_ID = "GOB";
  private static final String SUB_CLIENT_ID = "GSB";

  @Value("${mqtt.broker-url}")
  private String brokerUrl;

  @Value("${mqtt.topic}")
  private String topic;

  public MqttConfig(MqttService mqttService) {
    this.mqttService = mqttService;
  }

  @Bean
  public MqttPahoClientFactory mqttPahoClientFactory() {
    DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
    factory.setConnectionOptions(mqttConnectOptions());
    return factory;
  }

  @Bean
  public MessageChannel mqttOutboundChannel() {
    return new DirectChannel();
  }

  @Bean
  @ServiceActivator(inputChannel = "mqttOutboundChannel")
  public MessageHandler mqttOutbound() {
    MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(PUB_CLIENT_ID, mqttPahoClientFactory());
    messageHandler.setAsync(true);
    messageHandler.setDefaultTopic(topic);
    return messageHandler;
  }

  @Bean
  public MessageChannel mqttInboundChannel() {
    return new DirectChannel();
  }

  @Bean
  public MqttPahoMessageDrivenChannelAdapter inbound() {
    MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
        brokerUrl,
        SUB_CLIENT_ID,
        mqttPahoClientFactory(),
        topic
    );
    adapter.setCompletionTimeout(5000);
    adapter.setConverter(new DefaultPahoMessageConverter());
    adapter.setQos(1);
    adapter.setOutputChannel(mqttInboundChannel());
    return adapter;
  }

  @Bean
  @ServiceActivator(inputChannel = "mqttInboundChannel")
  public MessageHandler mqttInbound() {
    return new MqttListener(mqttService);
  }

  @Bean
  public MqttConnectOptions mqttConnectOptions() {
    MqttConnectOptions options = new MqttConnectOptions();
    options.setServerURIs(new String[]{brokerUrl});
    return options;
  }
}
